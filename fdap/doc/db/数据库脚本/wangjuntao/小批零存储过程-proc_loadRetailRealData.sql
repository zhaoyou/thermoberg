set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go






/**
		С����ʵʱ�����ϴ��洢����
		param : authCode
		param : {aid1,t1,latitude,latitude_dir,longitude,longitude_dir,time}
		return: 0 �ɹ� 1 ʧ��
		
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadRetailRealData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadRetailRealData]

**/
ALTER PROCEDURE [dbo].[proc_loadRetailRealData]
@pauthCode varchar(40),@psource varchar(4000),@pisOk int output,@pmsg varchar(400) output,@alarmRefs varchar(400) output
AS
BEGIN
	/*�������*/
	DECLARE @var_loopnumber int;
	set @var_loopnumber = 1;
	declare @var_loopindex int;
	set @var_loopindex = 1;
	declare @var_hisalarmtable varchar(50) ;
	set @var_hisalarmtable = '';

	declare @var_oid bigint;
	set @var_oid = -1;


		/*��������Ԥִ�е�sql���*/
		declare @var_addHisAlarm_sql nvarchar(400)
		set @var_addHisAlarm_sql = 'insert into @table (aiGUID,alarmData,startTime,alarmlevel,alarmtype,alarmstandard,flag) values (@In_aiGUID,@In_alarmData,@In_startTime,@In_alarmlevel,@In_alarmtype,@In_alarmstandard,0)' ;						/**������ʷ����ִ�е�sql���*/
		declare @var_updateHisAlarm_sql nvarchar(400)
		set @var_updateHisAlarm_sql = 'update @table set endTime =@In_endTime ,flag = 1 where aiguid = @In_aiguid and flag = 0';	





		set @pmsg = '';
		set @alarmRefs = '';
	/*��������*/
	begin TRANSACTION ;
		begin try
			set @var_loopnumber = dbo.func_stringtotal(@psource,';');

			set @var_oid = dbo.func_get_oid(@pauthCode);

			if(@var_oid=-1 or (@var_loopnumber is null))
				begin
					rollback TRANSACTION;
					set @pisOk =1;
					set @pmsg = @pmsg+'��Ȩ��Ƿ��򴫵�����Ϊ��';
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
						/*refai_whileѭ�����õ��ı���*/
						declare @aid int;
						set @aid = -1;
						/*̽ͷ�������ҵ��id*/
						declare @var_aid1 int;
						set @var_aid1 = -1;
						
						/*refai_whileѭ�����õ��ı���*/
						declare @t_value decimal(5,2);
						/*̽ͷ��ֵ*/
						declare @t1_value decimal(5,2);
						declare @var_latitude decimal(8,4),@var_longitude decimal(9,4);
						declare @var_latitude_dir int,@var_longitude_dir int;
						declare @var_time varchar(25);

						declare @var_refid bigint;
						set @var_refid = -1;

						/*̽ͷ��guid��ʶ*/
						DECLARE @var_aiguid varchar(40);
						set @var_aiguid = '';

						/*̽ͷ��guid��ʶ*/
						DECLARE @var_aiguid_t1 varchar(40);
						set @var_aiguid_t1 = '';
						

						/*̽ͷ�ı������ͣ��Լ���һ�εı�������*/
						DECLARE @var_alarmType int,@var_prealarmtype int ;
						/*̽ͷ�Ƿ��Ѿ���������*/
						DECLARE @var_isrealalarm int ;
						/*̽ͷ�����Ƿ��Ѿ�������ʱ*/
						DECLARE @var_isOverDelay int ;

						/*С����ı���״̬,Ĭ��Ϊ����*/
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
								set @pmsg = @pmsg+'�Ҳ�����ҵ��Ӧ��̽ͷ';
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

										/*��ǰ̽ͷ��������ֹͣ����*/
										IF(@var_alarmType =0 or @var_runstatus=1)
											begin
												if(@var_prealarmtype = 0)
													set @pmsg = @pmsg+convert(varchar(20),@var_refaiindex)+'��̽ͷ����';
												else
													begin	
														set @pmsg = @pmsg+convert(varchar(20),@var_refaiindex)+'��̽ͷ��������';
														set @var_isrealalarm = dbo.func_get_alarmIsRealAlarm(@var_aiguid);
														IF(@var_isrealalarm = 0)
															/*ɾ��ʵʱ�����еı���(δ����ʱ)��ʶ*/
															DELETE from fdaprealalarm where  aiguid = @var_aiguid ;
														ELSE
															BEGIN
																/*ɾ��ʵʱ�����еı���(δ����ʱ)��ʶ*/
																DELETE from fdaprealalarm where  aiguid = @var_aiguid ;

																/*����ִ�и���*/
																
																exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;
															END
													end
											end							
										/*��ǰ̽ͷ����*/
										ELSE
											BEGIN
												/*������������һ�²���*/
												if(@var_runstatus=2)
													begin
														/*��ǰ̽ͷ�ϴλ�δ����*/
														if(@var_prealarmtype = 0) 
															begin
																set @pmsg = @pmsg+'̽ͷ��һ�α���' ;
																/*�����µı�����¼*/
																INSERT into fdaprealalarm (aiguid,orgid,alarmdata,time,isrealalarm,alarmlevel,alarmtype,alarmstandard)
																VALUES (@var_aiguid,@var_oid,@t_value,@var_time,0,dbo.func_getAlarmLevel(@var_alarmType),@var_alarmType,dbo.func_get_alarmOverValue(@var_aiguid ,@t_value ,@var_alarmType));
															end
														/*��ǰ̽ͷ�Ѿ�������*/
														else
															begin	
																set @var_isrealalarm = dbo.func_get_alarmIsRealAlarm(@var_aiguid);
																/*�ж�̽ͷ���α��������Ƿ�һ��,�����һ��*/
																IF(@var_alarmType = @var_prealarmtype) 
																	BEGIN
																		/*̽ͷ�ϴβ�����������*/
																		if(@var_isrealalarm = 0) 
																			begin
																				/*̽ͷ��ǰ�����Ƿ񳬹���ʱ*/
																				set @var_isOverDelay = dbo.func_get_IsOverAlarmDelay(@var_aiguid,@var_alarmType,@var_time);
																				/*if @var_isOverDelay <> 0 THEN
																				#		set @pmsg = @pmsg+'dadf';
																				#ELSE
																				#		set @pmsg = @pmsg+'dddddddddd';
																				#end if;*/
																				set @pmsg = @pmsg+'��ʱ�������'+convert(varchar(20),@var_isOverDelay);
																				/*#��ǰ����δ������ʱ*/
																				IF @var_isOverDelay = 0 
																					BEGIN
																						set @pmsg = @pmsg+'δ������ʱ����������';
																						update fdaprealalarm set alarmdata = @t_value,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@t_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 0 ;
																					END
																				ELSE
																					BEGIN
																						set @pmsg = @pmsg+'������ʱ��������ʷ������¼';
																						set @alarmRefs = @alarmRefs+convert(varchar(20),@var_refid)+',';
																						set @var_refalarmstatus = 0;

																						/*#����ʱ������ʵʱ�������ݡ������ʷ��������*/
																						update fdaprealalarm set isrealalarm =1 ,alarmdata = @t_value ,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@t_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 0 ;

																						/*#����ִ�������ʷ������¼*/
																						
																						set @var_alarmlevel = dbo.func_getAlarmLevel(@var_alarmType);
																						set @var_alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid ,@t_value,@var_alarmType);
																						exec sp_executesql @var_addHisAlarm_sql,
																							N'@In_aiGUID varchar(40),@In_alarmData decimal(5,2),@In_startTime varchar(25),@In_alarmlevel int,@In_alarmtype int,@In_alarmstandard decimal(5,2)',
																							@var_aiguid,@t_value,@var_time,@var_alarmlevel,@var_alarmType,@var_alarmstandard;
																					END
																			end
																		/*#�ϴ�����������*/
																		else
																			begin
																				set @pmsg = @pmsg+'����������ʱ,��������';
																				set @var_refalarmstatus = 0;
																				update fdaprealalarm set alarmdata = @t_value,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@t_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 1 ;
																			end
																	END
																/*#������̽ͷ�������Ͳ�һ��*/
																ELSE
																	BEGIN
																		set @pmsg = @pmsg+'���α������Ͳ�һ��';
																		/*#̽ͷ�ϴβ�����������*/

																			DELETE FROM fdaprealalarm where aiGuid = @var_aiguid;
																			INSERT INTO fdaprealalarm (aiguid,orgid,alarmdata,time,isrealalarm,
																			alarmlevel,alarmtype,alarmstandard) VALUES (@var_aiguid,@var_oid,@t_value,@var_time,0,dbo.func_getAlarmLevel(@var_alarmType),@var_alarmType,dbo.func_get_alarmOverValue(@var_aiguid ,@t_value ,@var_alarmType));

																		if(@var_isrealalarm = 1) 
																			begin
																				/*#����ʵʱ���������ݺͱ������ͣ������ϴε���ʷ��������*/
																				/*#����ִ�и���*/
																				
																				exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;

																			end
																	END
															end 
													end
											END 

										set @var_refaiindex = @var_refaiindex+1;
									end
								set @pmsg = @pmsg+' ����С����ʵʱ����';
								/*#ɾ��С����ʵʱ��¼*/
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
		/*������������*/
		/****/
		begin catch
			rollback TRANSACTION;										/*���۷���ʲô���󶼻ع�����*/
			set @pisOk = 1 ; 							/*�����������״̬��ʧ��*/
			set @pmsg =@pmsg+ '�����ع�����������' ;
		end catch
end ;



