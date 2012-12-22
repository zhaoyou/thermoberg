set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go






/**
		小批零实时数据上传存储过程
		param : authCode
		param : {aid1,t1,latitude,latitude_dir,longitude,longitude_dir,time}
		return: 0 成功 1 失败
		
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadRetailRealData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadRetailRealData]

**/
ALTER PROCEDURE [dbo].[proc_loadRetailRealData]
@pauthCode varchar(40),@psource varchar(4000),@pisOk int output,@pmsg varchar(400) output,@alarmRefs varchar(400) output
AS
BEGIN
	/*定义变量*/
	DECLARE @var_loopnumber int;
	set @var_loopnumber = 1;
	declare @var_loopindex int;
	set @var_loopindex = 1;
	declare @var_hisalarmtable varchar(50) ;
	set @var_hisalarmtable = '';

	declare @var_oid bigint;
	set @var_oid = -1;


		/*定义三个预执行的sql语句*/
		declare @var_addHisAlarm_sql nvarchar(400)
		set @var_addHisAlarm_sql = 'insert into @table (aiGUID,alarmData,startTime,alarmlevel,alarmtype,alarmstandard,flag) values (@In_aiGUID,@In_alarmData,@In_startTime,@In_alarmlevel,@In_alarmtype,@In_alarmstandard,0)' ;						/**增加历史报警执行的sql语句*/
		declare @var_updateHisAlarm_sql nvarchar(400)
		set @var_updateHisAlarm_sql = 'update @table set endTime =@In_endTime ,flag = 1 where aiguid = @In_aiguid and flag = 0';	





		set @pmsg = '';
		set @alarmRefs = '';
	/*开启事务*/
	begin TRANSACTION ;
		begin try
			set @var_loopnumber = dbo.func_stringtotal(@psource,';');

			set @var_oid = dbo.func_get_oid(@pauthCode);

			if(@var_oid=-1 or (@var_loopnumber is null))
				begin
					rollback TRANSACTION;
					set @pisOk =1;
					set @pmsg = @pmsg+'授权码非法或传递数据为空';
				end
			else
				begin
					declare @var_refaiindex int;
					set @var_refaiindex = 1;

					set @var_hisalarmtable = 'fdaphisalarm_'+convert(varchar(20),@var_oid);
					set @var_addHisAlarm_sql = replace(@var_addHisAlarm_sql,'@table',@var_hisalarmtable);
					set @var_updateHisAlarm_sql = replace(@var_updateHisAlarm_sql,'@table',@var_hisalarmtable);

					set @pmsg = @pmsg+'start ....' ;

					while @var_loopindex<=@var_loopnumber
					  begin
						/*refai_while循环中用到的变量*/
						declare @aid int;
						set @aid = -1;
						/*探头相对于企业的id*/
						declare @var_aid1 int;
						set @var_aid1 = -1;
						
						/*refai_while循环中用到的变量*/
						declare @t_value decimal(5,2);
						/*探头的值*/
						declare @t1_value decimal(5,2);
						declare @var_latitude decimal(8,4),@var_longitude decimal(9,4);
						declare @var_latitude_dir int,@var_longitude_dir int;
						declare @var_time varchar(25);

						declare @var_refid bigint;
						set @var_refid = -1;

						/*探头的guid标识*/
						DECLARE @var_aiguid varchar(40);
						set @var_aiguid = '';

						/*探头的guid标识*/
						DECLARE @var_aiguid_t1 varchar(40);
						set @var_aiguid_t1 = '';
						

						/*探头的报警类型，以及上一次的报警类型*/
						DECLARE @var_alarmType int,@var_prealarmtype int ;
						/*探头是否已经真正报警*/
						DECLARE @var_isrealalarm int ;
						/*探头报警是否已经超过延时*/
						DECLARE @var_isOverDelay int ;

						/*小批零的报警状态,默认为正常*/
						DECLARE @var_refalarmstatus int;
						set @var_refalarmstatus = 1;

						declare @var_alarmlevel int;
						set @var_alarmlevel = -1;
						declare @var_alarmstandard decimal(5,2);

						set @var_refaiindex = 1;

						declare @var_runstatus int;

						set @var_aid1 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',1);
						
						set @t1_value = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',2);
						

						set @var_latitude = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',3);
						set @var_latitude_dir = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',4);
						set @var_longitude = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',5);
						set @var_longitude_dir = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',6);
						set @var_time = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',7);
						set @var_runstatus = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',8);

						set @var_aiguid_t1 = dbo.func_aiguid_byoid(@var_oid,@var_aid1);
						

						set @var_refid = dbo.func_get_refid_byaiguid(@var_aiguid_t1);

						if(@var_aiguid_t1 = ''  or @var_runstatus = '')
							begin
								set @pmsg = @pmsg+'找不到企业对应的探头';
								select id from fdapuser5;
							end
						else
							begin
								while @var_refaiindex<=1 
									begin
										set @aid = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',@var_refaiindex);
										set @t_value = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',(3+@var_refaiindex));

										set @var_aiguid = dbo.func_aiguid_byoid(@var_oid,@aid);

										set @var_alarmType = dbo.func_get_ai_alarmstatus(@var_aiguid,@t_value);
										set @var_prealarmtype = dbo.func_get_ai_prealarmstatus(@var_aiguid);

										/*当前探头正常或者停止运行*/
										IF(@var_alarmType =0 or @var_runstatus=1)
											begin
												if(@var_prealarmtype = 0)
													set @pmsg = @pmsg+convert(varchar(20),@var_refaiindex)+'号探头正常';
												else
													begin	
														set @pmsg = @pmsg+convert(varchar(20),@var_refaiindex)+'号探头结束报警';
														set @var_isrealalarm = dbo.func_get_alarmIsRealAlarm(@var_aiguid);
														IF(@var_isrealalarm = 0)
															/*删除实时报警中的报警(未超延时)标识*/
															DELETE from fdaprealalarm where  aiguid = @var_aiguid ;
														ELSE
															BEGIN
																/*删除实时报警中的报警(未超延时)标识*/
																DELETE from fdaprealalarm where  aiguid = @var_aiguid ;

																/*定义执行更新*/
																
																exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;
															END
													end
											end							
										/*当前探头报警*/
										ELSE
											BEGIN
												/*运行正常才做一下操作*/
												if(@var_runstatus=2)
													begin
														/*当前探头上次还未报警*/
														if(@var_prealarmtype = 0) 
															begin
																set @pmsg = @pmsg+'探头第一次报警' ;
																/*插入新的报警记录*/
																INSERT into fdaprealalarm (aiguid,orgid,alarmdata,time,isrealalarm,alarmlevel,alarmtype,alarmstandard)
																VALUES (@var_aiguid,@var_oid,@t_value,@var_time,0,dbo.func_getAlarmLevel(@var_alarmType),@var_alarmType,dbo.func_get_alarmOverValue(@var_aiguid ,@t_value ,@var_alarmType));
															end
														/*当前探头已经报过警*/
														else
															begin	
																set @var_isrealalarm = dbo.func_get_alarmIsRealAlarm(@var_aiguid);
																/*判断探头两次报警类型是否一致,如果是一致*/
																IF(@var_alarmType = @var_prealarmtype) 
																	BEGIN
																		/*探头上次不是真正报警*/
																		if(@var_isrealalarm = 0) 
																			begin
																				/*探头当前报警是否超过延时*/
																				set @var_isOverDelay = dbo.func_get_IsOverAlarmDelay(@var_aiguid,@var_alarmType,@var_time);
																				/*if @var_isOverDelay <> 0 THEN
																				#		set @pmsg = @pmsg+'dadf';
																				#ELSE
																				#		set @pmsg = @pmsg+'dddddddddd';
																				#end if;*/
																				set @pmsg = @pmsg+'延时计算结束'+convert(varchar(20),@var_isOverDelay);
																				/*#当前报警未超过延时*/
																				IF @var_isOverDelay = 0 
																					BEGIN
																						set @pmsg = @pmsg+'未超过延时，报警继续';
																						update fdaprealalarm set alarmdata = @t_value,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@t_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 0 ;
																					END
																				ELSE
																					BEGIN
																						set @pmsg = @pmsg+'超过延时，插入历史报警记录';
																						set @alarmRefs = @alarmRefs+convert(varchar(20),@var_refid)+',';
																						set @var_refalarmstatus = 0;

																						/*#超延时，更新实时报警数据、添加历史报警数据*/
																						update fdaprealalarm set isrealalarm =1 ,alarmdata = @t_value ,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@t_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 0 ;

																						/*#定义执行添加历史报警记录*/
																						
																						set @var_alarmlevel = dbo.func_getAlarmLevel(@var_alarmType);
																						set @var_alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid ,@t_value,@var_alarmType);
																						exec sp_executesql @var_addHisAlarm_sql,
																							N'@In_aiGUID varchar(40),@In_alarmData decimal(5,2),@In_startTime varchar(25),@In_alarmlevel int,@In_alarmtype int,@In_alarmstandard decimal(5,2)',
																							@var_aiguid,@t_value,@var_time,@var_alarmlevel,@var_alarmType,@var_alarmstandard;
																					END
																			end
																		/*#上次是真正报警*/
																		else
																			begin
																				set @pmsg = @pmsg+'报警超过延时,报警继续';
																				set @var_refalarmstatus = 0;
																				update fdaprealalarm set alarmdata = @t_value,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@t_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 1 ;
																			end
																	END
																/*#若两次探头报警类型不一致*/
																ELSE
																	BEGIN
																		set @pmsg = @pmsg+'两次报警类型不一致';
																		/*#探头上次不是真正报警*/

																			DELETE FROM fdaprealalarm where aiGuid = @var_aiguid;
																			INSERT INTO fdaprealalarm (aiguid,orgid,alarmdata,time,isrealalarm,
																			alarmlevel,alarmtype,alarmstandard) VALUES (@var_aiguid,@var_oid,@t_value,@var_time,0,dbo.func_getAlarmLevel(@var_alarmType),@var_alarmType,dbo.func_get_alarmOverValue(@var_aiguid ,@t_value ,@var_alarmType));

																		if(@var_isrealalarm = 1) 
																			begin
																				/*#更新实时报警的数据和报警类型，结束上次的历史报警数据*/
																				/*#定义执行更新*/
																				
																				exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;

																			end
																	END
															end 
													end
											END 

										set @var_refaiindex = @var_refaiindex+1;
									end
								set @pmsg = @pmsg+' 更新小批零实时数据';
								/*#删除小批零实时记录*/
								delete from fdapretailrealdata where refId = @var_refid;
								IF(@var_refalarmstatus = 0) 
									BEGIN
										insert into fdapretailrealdata(refId,aid1,t1,latitude,latitude_dir,longitude,longitude_dir,Time,isalarm) 
										values(@var_refid,@var_aid1,@t1_value,@var_latitude,@var_latitude_dir,@var_longitude,@var_longitude_dir,@var_time,0);
									END
								ELSE
									BEGIN
										insert into fdapretailrealdata(refId,aid1,t1,latitude,latitude_dir,longitude,longitude_dir,Time,isalarm) 
										values(@var_refid,@var_aid1,@t1_value,@var_latitude,@var_latitude_dir,@var_longitude,@var_longitude_dir,@var_time,1);
									END
							end
						set @var_loopindex = @var_loopindex+1;
					  end;
					set @pisOk = 0 ;
					COMMIT TRANSACTION;
				end;
		end try
		/*定义错误处理程序*/
		/****/
		begin catch
			rollback TRANSACTION;										/*无论发生什么错误都回滚事务*/
			set @pisOk = 1 ; 							/*代表操作操作状态码失败*/
			set @pmsg =@pmsg+ '操作回滚，发生错误' ;
		end catch
end ;



