set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go



/**
		冷库实时数据上次存储过程
		param : authCode
		param : {id,VALUE,time,status}
		return: 0 成功 1 失败
		
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadRefRealData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadRefRealData]

**/
ALTER PROCEDURE [dbo].[proc_loadRefRealData] 
@pauthCode varchar(40),@psource varchar(4000),@Time varchar(25),@pisOk int output,@pmsg varchar(400) output,@alarmRefs varchar(400) output
AS
BEGIN
		/*定义变量*/
		declare @var_loopNumber int ;
		set @var_loopNumber = 1;
		declare @var_loopIndex int;
		set @var_loopIndex = 1;
		/*declare @var_hisdata_table varchar(50) default '' ;
		declare @var_hisalarm_table varchar(50) default '' ;*/

		declare @var_oid bigint;
		set @var_oid = -1;


		/*定义三个预执行的sql语句*/
		declare @var_addHisAlarm_sql nvarchar(400) 
		set @var_addHisAlarm_sql ='insert into @table (aiGUID,alarmData,startTime,alarmlevel,alarmtype,alarmstandard,flag) values (@In_aiGUID,@In_alarmData,@In_startTime,@In_alarmlevel,@In_alarmtype,@In_alarmstandard,0)' ;						/**增加历史报警执行的sql语句*/
		declare @var_updateHisAlarm_sql nvarchar(400) 
		set @var_updateHisAlarm_sql = 'update @table set endTime =@In_endTime ,flag = 1 where aiguid = @In_aiguid and flag = 0';						/**更新历史报警执行的sql语句*/
		declare @var_addHisdata_sql nvarchar(400)
		set @var_addHisdata_sql = 'insert into @table (aiGUID,data,IsAlarm,time) values (@In_aiGUID,@In_data,@In_IsAlarm,@In_time)' ;					/**增加历史数据执行的sql语句*/

		/*定义历史报警表和历史数据表*/
		DECLARE @var_histable varchar(30),@var_hisalarmtable varchar(30) ;
		

		
		/*开启事务*/
		BEGIN TRANSACTION ;
			BEGIN TRY
				set @pmsg = '';
				set @alarmRefs = '';
			
				set @var_loopNumber  = dbo.func_stringtotal(@psource,';') ;

				set @var_oid = dbo.func_get_oid(@pauthCode) ;
				
				/*验证企业是否合法*/
				IF @var_oid =-1 or (@var_loopNumber is null) 
					BEGIN
						ROLLBACK TRANSACTION;
						set @pmsg = '授权码非法或传递数据为空';
						set @pisOk = 1 ;
					END
				ELSE 
						BEGIN
							
							/*设置表名*/
							set @var_histable = 'fdaprefhisdata_'+convert(varchar(20),@var_oid) ;
							set @var_hisalarmtable = 'fdaphisalarm_'+convert(varchar(20),@var_oid) ;
								
							
							
							/*定义执行动态表的sql语句*/
							set @var_addHisAlarm_sql =  replace(@var_addHisAlarm_sql,'@table',@var_hisalarmtable) ;
							set @var_updateHisAlarm_sql = replace(@var_updateHisAlarm_sql,'@table',@var_hisalarmtable) ;
							set @var_addHisdata_sql = replace(@var_addHisdata_sql,'@table',@var_histable) ;
							
							
						
							set @pmsg = '开始循环探头集合' ;
							WHILE @var_loopIndex <= @var_loopNumber
								BEGIN
									DECLARE @var_reid int  ;
									DECLARE @var_value decimal(5,2) ;
									DECLARE	@var_time varchar(25) ;
									DECLARE @var_status int ;

									/*探头的guid标识*/
									DECLARE @var_aiguid varchar(40) ;
									set @var_aiguid = '';
									/*探头的报警类型，以及上一次的报警类型*/
									DECLARE @var_alarmType int,@var_prealarmtype int ;
									/*探头是否已经真正报警*/
									DECLARE @var_isrealalarm int ;		
									/*探头报警是否已经超过延时*/
									DECLARE @var_isOverDelay int ;
									/*探头的历史报警状态,默认为正常*/
									DECLARE @var_aiHisAlarmStatus	int;
									set @var_aiHisAlarmStatus = 1;
									
									declare @var_alarmlevel int;
									set @var_alarmlevel = -1;
									declare @var_alarmstandard decimal(5,2);

									/*解析数据*/
									set @var_reid = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',1) ;
									set @var_value = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',2) ;
									set @var_status = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',3) ; 
									set @var_time = @Time;
									

									
									

									/*验证当前探头是否合法*/
									set @var_aiguid = dbo.func_aiguid_byoid(@var_oid,@var_reid) ;
									
										if(@var_aiguid='') 
											begin
												/*探头标识无法获取，手动引发错误*/
												set @pmsg = '找不到企业对应的探头' ;
												select id from fdapuser5 ;
											end
										else 
											begin
												/*获取当前探头的报警状态*/
												set @var_alarmType = dbo.func_get_ai_alarmstatus(@var_aiguid,@var_value) ;
												set @var_prealarmtype = dbo.func_get_ai_prealarmstatus(@var_aiguid) ;
													/*判断是否为补数据*/
													if @var_status = 0 or @var_status = 1
														begin
															/*当前探头正常*/
															if @var_alarmType = 0 
																begin
																	/*设置历史数据报警状态*/
																	set @var_aiHisAlarmStatus = 1 ;

																	/*判断探头的还未报过警*/
																	IF @var_prealarmtype = 0 
																		BEGIN
																			set @pmsg = '探头一直正常' ;
																		END
																	/*当前探头已经报过警*/
																	ELSE
																		BEGIN
																			set @var_isrealalarm = dbo.func_get_alarmIsRealAlarm(@var_aiguid) ;
																			/*判断之前的报警是否为真正的报警*/
																			IF @var_isrealalarm = 0 
																				BEGIN
																					/*删除实时报警中的报警(未超延时)标识*/
																					DELETE from fdaprealalarm where  aiguid = @var_aiguid ;
																				END
																			ELSE
																				BEGIN
																					/*删除实时报警中的报警(超过延时)标识*/
																					DELETE from fdaprealalarm where  aiguid = @var_aiguid ;
																							
																					/*定义执行更新*/
																					
																					exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;
																					
																				END	

																		END
																end
															/*当前探头发生报警*/
															else 
																begin
																	/*设置历史数据报警状态*/
																	set @var_aiHisAlarmStatus = 1 ;

																	/*判断探头的还未报过警*/														
																	IF @var_prealarmtype = 0 
																		BEGIN
																			set @pmsg = '探头第一次报警' ;
																			/*插入新的报警记录*/
																			INSERT into fdaprealalarm (aiguid,orgid,alarmdata,time,isrealalarm,alarmlevel,alarmtype,alarmstandard)
																			VALUES (@var_aiguid,@var_oid,@var_value,@var_time,0,dbo.func_getAlarmLevel(@var_alarmType),@var_alarmType,dbo.func_get_alarmOverValue(@var_aiguid ,@var_value ,@var_alarmType));
																		END		
																	/*当前探头已经报过警*/
																	ELSE
																		BEGIN	
																			set @var_isrealalarm = dbo.func_get_alarmIsRealAlarm(@var_aiguid) ;

																			/*判断当前探头的前后两次报警的类型是否一致*/
																			IF (@var_alarmType = @var_prealarmtype)
																				BEGIN
																					/*判断是不是真正的报警(0未真正报警,1真正报警)*/
																					IF @var_isrealalarm = 0
																						BEGIN
																							/*判断当前报警是否超过延时*/
																								
																							set @var_isOverDelay = dbo.func_get_IsOverAlarmDelay(@var_aiguid,@var_alarmType,@var_time) ;
																											
																							IF @var_isOverDelay = 0 
																								BEGIN
																									set @pmsg =  '未超延时，继续报警' ;
																								END
																							ELSE
																								BEGIN
																									set @pmsg = '超过延时,插入历史报警' ;

																									set @alarmRefs = @alarmRefs+convert(varchar(20),dbo.func_get_refid_byaiguid(@var_aiguid))+',';
																									/*设置历史数据报警状态*/
																									set @var_aiHisAlarmStatus = 0 ;

																									/*超延时，更新实时报警数据、添加历史报警数据	*/
																									update fdaprealalarm set isrealalarm =1 ,alarmdata = @var_value ,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@var_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 0 ;

																									
																									set @var_alarmlevel = dbo.func_getAlarmLevel(@var_alarmType);
																									set @var_alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid ,@var_value,@var_alarmType);
																									exec sp_executesql @var_addHisAlarm_sql,
																										N'@In_aiGUID varchar(40),@In_alarmData decimal(5,2),@In_startTime varchar(25),@In_alarmlevel int,@In_alarmtype int,@In_alarmstandard decimal(5,2)',
																										@var_aiguid,@var_value,@var_time,@var_alarmlevel,@var_alarmType,@var_alarmstandard;
																				
																								END 
																						END
																					/*真正的报警*/
																					ELSE
																						BEGIN
																							set @pmsg =  '报警超过延时,报警继续' ;
																							/*设置历史数据报警状态*/
																							set @var_aiHisAlarmStatus = 0 ;
																							update fdaprealalarm set alarmdata = @var_value,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@var_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 1 ;
																												
																						END 
																				END			
																			/*两次报警类型前后不一致*/
																			ELSE
																				BEGIN
																					set @pmsg = '前后两次报警类型不同' ;
																					/*判断是不是真正的报警 (0未真正报警 ，1 真正报警)
																					之前并未真正报警,删除原先的记录，重新插入一条新的报警标识*/
																					DELETE from fdaprealalarm where aiguid = @var_aiguid  ;

																					INSERT INTO fdaprealalarm (aiguid,orgid,alarmdata,time,isrealalarm,
																						alarmlevel,alarmtype,alarmstandard) VALUES (@var_aiguid,@var_oid,@var_value,@var_time,0,dbo.func_getAlarmLevel(@var_alarmType),@var_alarmType,dbo.func_get_alarmOverValue(@var_aiguid ,@var_value ,@var_alarmType));
																													
																					IF @var_isrealalarm = 1 
																						BEGIN																						
																							/*更新实时报警的数据和报警类型，结束上次的历史报警数据*/
																							/*定义执行更新*/
																							
																							exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;
																																												
																						END
																				END
																				
																		END	
																end 
																				
															/*删除冷库探头的实时数据*/
															DELETE from fdaprefrealdata where aiguid = @var_aiguid ;
															IF @var_aiHisAlarmStatus = 0 
																BEGIN
																	INSERT INTO fdaprefrealdata (aiguid ,data,time,isalarm) VALUES (@var_aiguid,@var_value,@var_time,0) ;
																END
															ELSE
																BEGIN
																	INSERT INTO fdaprefrealdata (aiguid ,data,time,isalarm) VALUES (@var_aiguid,@var_value,@var_time,1) ;
																END
														end;					
															
												/*如果冷库的状态为启用0 ,插入历史数据*/
												if (@var_status = 0 or @var_status = 2)
													begin		
																
														exec sp_executesql @var_addHisdata_sql,
															N'@In_aiGUID varchar(40),@In_data decimal(5,2),@In_IsAlarm int,@In_time varchar(25)',
															@var_aiguid,@var_value,@var_aiHisAlarmStatus,@var_time;
															
																			
													end;
											end	/*结束于判断探头是否合法*/
										
									set @var_loopIndex = @var_loopIndex + 1 ;
								END ;
							
							 set @pisOk = 0 ;
							COMMIT TRANSACTION;
						END ;
			END TRY
			/*定义错误处理程序*/
				/****/
			BEGIN CATCH
					rollback TRANSACTION;						/*无论发生什么错误都回滚事务*/
					set @pisOk = 1 ;							/*代表操作操作状态码失败*/
					set @pmsg = @pmsg+'操作回滚，发生错误' ;
			END CATCH
END ;










