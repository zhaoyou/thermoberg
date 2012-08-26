USE [tbcc_fdap]
GO
set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go




/**
		��������ʷ���ݱ�����ʶλ�洢����
		param : @source			{oid,aid1,aid2,aid3,startupid}
		return: 0 �ɹ� 1 ʧ��
		
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_dealCarhis]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_dealCarhis]

**/
CREATE PROCEDURE [dbo].[proc_dealCarhis]
@source varchar(50),@pis_ok int output
AS
BEGIN
	/*��������*/
	begin TRANSACTION ;
	begin try
		declare @var_time varchar(25),@alarm_time1 varchar(25),@alarm_time2 varchar(25),@alarm_time3 varchar(25),@var_t1 decimal(5,2),@var_t2 decimal(5,2),@var_t3 decimal(5,2),@var_isalarm int;

		declare @var_aiguid1 varchar(36),@var_aiguid2 varchar(36),@var_aiguid3 varchar(36);

		declare @var_id bigint,@var_oid bigint,@startupid bigint;

		declare @var_alarmstatus1 int,@var_alarmstatus2 int,@var_alarmstatus3 int;

		declare @var_overDelay1 int,@var_overDelay2 int,@var_overDelay3 int;

		declare @sql NVARCHAR(400),@up_sql NVARCHAR(400),@var_carhisdataTable Varchar(50);
		
		set @var_oid = dbo.func_get_split_string(@source,',',1);
		set @startupid = dbo.func_get_split_string(@source,',',5);

		set @var_aiguid1 = dbo.func_aiguid_byoid(@var_oid,dbo.func_get_split_string(@source,',',2));
		set @var_aiguid2 = dbo.func_aiguid_byoid(@var_oid,dbo.func_get_split_string(@source,',',3));
		set @var_aiguid3 = dbo.func_aiguid_byoid(@var_oid,dbo.func_get_split_string(@source,',',4));

		set @var_carhisdataTable = 'fdapcarhisdata_'+convert(varchar(20),@var_oid);

		/*print @var_aiguid1+' '+@var_aiguid2+' '+@var_aiguid3;*/
		set @sql = 'select id,t1,t2,t3,isalarm,time from '+@var_carhisdataTable+' where startupid = '+convert(varchar(10),@startupid)+' order by time';


		declare @tables_t TABLE(id bigint,t1 decimal(5,2),t2 decimal(5,2),t3 decimal(5,2),isalarm int,time varchar(25));
		insert into @tables_t exec sp_executesql @sql;


		declare car_hisdata_cursor CURSOR for
		select id,t1,t2,t3,isalarm,time from @tables_t;

		open car_hisdata_cursor

		FETCH NEXT from car_hisdata_cursor
		INTO @var_id,@var_t1,@var_t2,@var_t3,@var_isalarm,@var_time

		while @@FETCH_STATUS =0
		begin
			/*print 'aaa:'+convert(varchar(25),@var_id)+' '+convert(varchar(8),@var_t1)+' '+convert(varchar(8),@var_t2)+' '+convert(varchar(8),@var_t3)+' '+convert(varchar(8),@var_isalarm)+' '+@var_time;*/
			
			set @var_alarmstatus1 = dbo.func_get_ai_alarmstatus(@var_aiguid1,@var_t1);
			set @var_alarmstatus2 = dbo.func_get_ai_alarmstatus(@var_aiguid2,@var_t2);
			set @var_alarmstatus3 = dbo.func_get_ai_alarmstatus(@var_aiguid3,@var_t3);
			
			if @var_alarmstatus1=0 begin
					set @alarm_time1 = '';
					set @var_overDelay1 = 0;
				end
			else begin
					if @alarm_time1='' begin
							set @alarm_time1=@var_time;
						end
					set @var_overDelay1 = dbo.func_get_carhis_IsOverAlarmDelay(@var_aiguid1,@var_alarmstatus1,@alarm_time1,@var_time);
				end

			if @var_alarmstatus2=0 begin
					set @alarm_time2 = '';
					set @var_overDelay1=0;
				end
			else begin
					if @alarm_time2='' begin
							set @alarm_time2=@var_time;
						end
					set @var_overDelay2 = dbo.func_get_carhis_IsOverAlarmDelay(@var_aiguid2,@var_alarmstatus2,@alarm_time2,@var_time);
				end

			if @var_alarmstatus3=0 begin
					set @alarm_time3 = '';
					set @var_overDelay1=0;
				end
			else begin
					if @alarm_time3='' begin
							set @alarm_time3=@var_time;
						end
					set @var_overDelay3 = dbo.func_get_carhis_IsOverAlarmDelay(@var_aiguid3,@var_alarmstatus3,@alarm_time3,@var_time);
				end
			
			
			if @var_isalarm <>0 and @var_isalarm<>1 begin
					if @var_overDelay1=1 or @var_overDelay2=1 or @var_overDelay3=1 begin
							set @up_sql = 'update '+@var_carhisdataTable+' set isalarm = 0 where id ='+convert(varchar(25),@var_id);
							exec sp_executesql @up_sql;
						end
					else begin
							set @up_sql = 'update '+@var_carhisdataTable+' set isalarm = 1 where id ='+convert(varchar(25),@var_id);
							exec sp_executesql @up_sql;
						end
				end

			FETCH NEXT from car_hisdata_cursor 
			INTO @var_id,@var_t1,@var_t2,@var_t3,@var_isalarm,@var_time
		end

		close car_hisdata_cursor
		deallocate car_hisdata_cursor;

		set @pis_ok = 0;
		COMMIT TRANSACTION;
	end try

	/*������������*/
	/****/
	begin catch
		rollback TRANSACTION;										/*���۷���ʲô���󶼻ع�����*/
		set @pis_ok = 1 ; 							/*�����������״̬��ʧ��*/
	end catch;	
end;
GO



/**
		������ʷ�����ϴ��洢����
		param : @pauthcode
		param : @psource {aid1,aid2,aid3,t1,t2,t3,latitude,Latitude_dir,longitude,longitude_dir,`time`,isalarm}
		return: 0 �ɹ� 1 ʧ��
		

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadCarHisData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadCarHisData]
**/

CREATE PROCEDURE [dbo].[proc_loadCarHisData]
 @pauthcode VARCHAR(40),@psource VARCHAR(4000),@pisok INT output,@pmsg VARCHAR(400) output,@dealhisMsg VARCHAR(1000) output
AS
BEGIN
		/*�������*/
		DECLARE @var_loopnumber int,@var_loopIndex INT;
		set @var_loopnumber = 1;
		set @var_loopIndex =1;
		DECLARE @var_oid BIGINT;
		set @var_oid = -1;
		DECLARE @var_carhisdataTable VARCHAR(50);
		set @var_carhisdataTable = '';

		DECLARE @var_carhisdata_sql NVARCHAR(400)
		set @var_carhisdata_sql= 'insert into @table (T1,T2,T3,StartupId,latitude,Latitude_dir,longitude,longitude_dir,time,isalarm) values(@In_t1,@In_t2,@In_t3,@In_StartupId,@In_latitude,@In_latitude_dir,@In_longitude,@In_longitude_dir,@In_time,@In_isalarm)';

		DECLARE @var_startup_table VARCHAR(50);
		set @var_startup_table= '';
		DECLARE @var_startup_sql nvarchar(400)
		set @var_startup_sql = 'select @out_startupid=startupid from @table where refid = @In_refid and ((startTime<= @In_startTime and endTime >= @In_startTime) or (startTime<= @In_startTime and endTime is null))';
		
		/*��������*/
	begin TRANSACTION ;
		begin try
			set @pmsg = '';
			set @dealhisMsg = '';
			set @var_loopnumber = dbo.func_stringtotal(@psource,';');
			
			set @var_oid = dbo.func_get_oid(@pauthcode);
			
			if(@var_oid=-1 or (@var_loopnumber is null))
				begin
					rollback TRANSACTION;
					set @pisok =1;
					set @pmsg = @pmsg+'��Ȩ��Ƿ��򴫵�����Ϊ��';
				end
			else
				begin
					set @dealhisMsg = @dealhisMsg+convert(varchar(20),@var_oid)+';';
					
					set @var_carhisdataTable = 'Fdapcarhisdata_'+convert(varchar(20),@var_oid);
					set @var_carhisdata_sql = replace(@var_carhisdata_sql,'@table',@var_carhisdataTable);
					set @var_startup_table = 'Fdapstartup_'+convert(varchar(20),@var_oid);
					set @var_startup_sql = replace(@var_startup_sql,'@table',@var_startup_table);

					WHILE @var_loopIndex<=@var_loopnumber
							BEGIN
									DECLARE @var_aid1 int,@var_aid2 int,@var_aid3 int;
									set @var_aid1=-1;
									set @var_aid2=-1;
									set @var_aid3=-1;
									DECLARE @var_t1 DECIMAL(5,2),@var_t2 DECIMAL(5,2),@var_t3 DECIMAL(5,2);
									
									DECLARE @var_latitude DECIMAL(8,4),@var_longitude DECIMAL(9,4);
									DECLARE @var_latitude_dir int,@var_longitude_dir int;
									set @var_latitude_dir=-1;
									set @var_longitude_dir=-1;
									DECLARE @var_time VARCHAR(25);
									set @var_time = '';
									DECLARE @var_isalarm int ;
									set @var_isalarm = 1;
									
									DECLARE @var_refid BIGINT;
									set @var_refid = -1;
									DECLARE @var_aiguid1 VARCHAR(40),@var_aiguid2 VARCHAR(40),@var_aiguid3 VARCHAR(40);
									set @var_aiguid1 = '';
									set @var_aiguid2 = '';
									set @var_aiguid3 = '';
									
									declare @var_startupid bigint;
									
									
									set @var_aid1 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',1);
									set @var_aid2 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',2);
									set @var_aid3 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',3);
									set @var_t1 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',4);
									set @var_t2 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',5);
									set @var_t3 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',6);
									

									set @var_latitude = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',7);
									set @var_latitude_dir = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',8);
									set @var_longitude = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',9);
									set @var_longitude_dir = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',10);
									set @var_time = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',11);

									set @var_isalarm = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',12);
								
									set @var_aiguid1 = dbo.func_aiguid_byoid(@var_oid,@var_aid1);
									set @var_aiguid2 = dbo.func_aiguid_byoid(@var_oid,@var_aid2);
									set @var_aiguid3 = dbo.func_aiguid_byoid(@var_oid,@var_aid3);

									set @var_refid = dbo.func_get_refid_byaiguid(@var_aiguid1);
									if(@var_aid1 = -1 or @var_aiguid1 = '' or @var_aiguid2 = '' or @var_aiguid3 = '') 
										begin
											set @pmsg = '�Ҳ�����ҵ��Ӧ��̽ͷ';
											select id from Fdapuser5;
										end
									else
										begin
											set @var_startupid = -1;
											/*set @pmsg = @pmsg+'������ʷ���ݶ�Ӧ����ͣ��¼id'+convert(varchar(20),@var_startupid);*/
											/*set @exec_startupsql = @var_startup_sql ;
											PREPARE exec_getStartupId from @exec_startupsql ;
											set @exec_refid = @var_refid;
											set @exec_time = @var_time ;
											EXECUTE exec_getStartupId using @exec_refid,@exec_time,@exec_time ;
											DEALLOCATE  PREPARE exec_getStartupId ;*/
											exec sp_executesql @var_startup_sql,N'@In_refid int,@In_startTime varchar(25),@out_startupid bigint output',@var_refid,@var_time,@var_startupid output
											

											
											if(@var_startupid is null or @var_startupid = -1)
												begin
													rollback TRANSACTION;
													set @pisok =1;
													set @pmsg = 'δ�ҵ���Ӧ����ͣ��¼';
												end
											ELSE
												BEGIN
													/*set @exec_carhisdatasql = @var_carhisdata_sql;
													
													PREPARE exec1 from @exec_carhisdatasql ;

													set @exec1_t1 = @var_t1;
													set @exec1_t2 = @var_t2;
													set @exec1_t3 = @var_t3;
													set @exec1_latitude = @var_latitude;
													set @exec1_latitude_dir = @var_latitude_dir;
													set @exec1_longitude = @var_longitude;
													set @exec1_longitude_dir = @var_longitude_dir;
													set @exec1_time = @var_time;
													set @exec1_isalarm = @var_isalarm;

													EXECUTE exec1 using @exec1_t1,@exec1_t2,@exec1_t3,@var_startupid,@exec1_latitude,@exec1_latitude_dir,@exec1_longitude,@exec1_longitude_dir,@exec1_time,@exec1_isalarm;

													DEALLOCATE  PREPARE exec1;*/
													/*set @pmsg = convert(varchar(4),@var_startupid);*/
													
													set @dealhisMsg =@dealhisMsg+convert(varchar(20),@var_aid1)+','+convert(varchar(20),@var_aid2)+','+convert(varchar(20),@var_aid3)+','+convert(varchar(20),@var_startupid)+';';
													
													exec sp_executesql @var_carhisdata_sql,
														N'@In_t1 DECIMAL(5,2),@In_t2 DECIMAL(5,2),@In_t3 DECIMAL(5,2),@In_StartupId bigint,@In_latitude DECIMAL(8,4),@In_latitude_dir int,@In_longitude DECIMAL(9,4),@In_longitude_dir int,@In_time VARCHAR(25),@In_isalarm int',
														@var_t1,@var_t2,@var_t3,@var_startupid,@var_latitude,@var_latitude_dir,@var_longitude,@var_longitude_dir,@var_time,@var_isalarm;
											
												END
										end 
									set @var_loopIndex = @var_loopIndex + 1;
							END
				end
			
			set @pisok = 0;
			COMMIT TRANSACTION;
		end try

		/*������������*/
		/****/
		begin catch
			rollback TRANSACTION;										/*���۷���ʲô���󶼻ع�����*/
			set @pisok = 1 ; 							/*�����������״̬��ʧ��*/
			set @pmsg =@pmsg+ '�����ع�����������' ;
		end catch;	
END;
GO



/**
		����ʵʱ�����ϴ��洢����
		param : authCode
		param : {aid1,aid2,aid3,t1,t2,t3,latitude,latitude_dir,longitude,longitude_dir,time}
		return: 0 �ɹ� 1 ʧ��
		
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadCarRealData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadCarRealData]

**/
CREATE PROCEDURE [dbo].[proc_loadCarRealData]
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
						/*����̽ͷ�������ҵ��id*/
						declare @var_aid1 int,@var_aid2 int,@var_aid3 int;
						set @var_aid1 = -1;
						set @var_aid2 = -1;
						set @var_aid3 = -1;
						/*refai_whileѭ�����õ��ı���*/
						declare @t_value decimal(5,2);
						/*����̽ͷ��ֵ*/
						declare @t1_value decimal(5,2),@t2_value decimal(5,2),@t3_value decimal(5,2);
						declare @var_latitude decimal(8,4),@var_longitude decimal(9,4);
						declare @var_latitude_dir int,@var_longitude_dir int;
						declare @var_time varchar(25);
						
						declare @var_refid bigint;
						set @var_refid = -1;

						/*̽ͷ��guid��ʶ*/
						DECLARE @var_aiguid varchar(40);
						set @var_aiguid = '';
						
						/*����̽ͷ��guid��ʶ*/
						DECLARE @var_aiguid_t1 varchar(40),@var_aiguid_t2 varchar(40),@var_aiguid_t3 varchar(40);
						set @var_aiguid_t1 = '';
						set @var_aiguid_t2 = '';
						set @var_aiguid_t3 = '';
						
						/*̽ͷ�ı������ͣ��Լ���һ�εı�������*/
						DECLARE @var_alarmType int,@var_prealarmtype int ;
						/*̽ͷ�Ƿ��Ѿ���������*/
						DECLARE @var_isrealalarm int ;
						/*̽ͷ�����Ƿ��Ѿ�������ʱ*/
						DECLARE @var_isOverDelay int ;
						
						/*���صı���״̬,Ĭ��Ϊ����*/
						DECLARE @var_refalarmstatus int;
						set @var_refalarmstatus = 1;
						
						declare @var_alarmlevel int;
						set @var_alarmlevel = -1;
						declare @var_alarmstandard decimal(5,2);
						
						set @var_refaiindex = 1;

						declare @var_runstatus int;

						set @var_aid1 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',1);
						set @var_aid2 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',2);
						set @var_aid3 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',3);
						set @t1_value = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',4);
						set @t2_value = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',5);
						set @t3_value = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',6);
			
						set @var_latitude = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',7);
						set @var_latitude_dir = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',8);
						set @var_longitude = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',9);
						set @var_longitude_dir = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',10);
						set @var_time = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',11);
						set @var_runstatus = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopindex),',',12);

						set @var_aiguid_t1 = dbo.func_aiguid_byoid(@var_oid,@var_aid1);
						set @var_aiguid_t2 = dbo.func_aiguid_byoid(@var_oid,@var_aid2);
						set @var_aiguid_t3 = dbo.func_aiguid_byoid(@var_oid,@var_aid3);
						
						set @var_refid = dbo.func_get_refid_byaiguid(@var_aiguid_t1);

						if(@var_aiguid_t1 = '' or @var_aiguid_t2 = '' or @var_aiguid_t3 = '' or @var_runstatus = '')
							begin
								set @pmsg = @pmsg+'�Ҳ�����ҵ��Ӧ��̽ͷ';
								select id from fdapuser5;
							end
						else
							begin
								while @var_refaiindex<=3 
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
																/*set @exec_updatesql = @var_updateHisAlarm_sql ;

																PREPARE exec from @exec_updatesql ;

																set @exec_time = @var_time ;
																set @exec_aiguid = @var_aiguid ;

																EXECUTE exec using @exec_time,@exec_aiguid ;

																DEALLOCATE  PREPARE exec ;*/
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
																						/*set @exec_addsql = @var_addHisAlarm_sql ;

																						PREPARE exec from @exec_addsql ;

																						set @exec_aiguid = @var_aiguid ;
																						set @exec_alarmdata = @t_value ;
																						set @exec_starttime = @var_time ;
																						set @exec_alarmlevel = dbo.func_getAlarmLevel(@var_alarmType) ;
																						set @exec_alarmtype = @var_alarmType ;
																						set @exec_alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid ,@t_value,@var_alarmType) ;

																						EXECUTE exec using @exec_aiguid,@exec_alarmdata,@exec_starttime,@exec_alarmlevel,@exec_alarmtype,@exec_alarmstandard ;

																						DEALLOCATE  PREPARE exec ;*/
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
																				/*set @exec_updatesql = @var_updateHisAlarm_sql ;

																				PREPARE exec from @exec_updatesql ;

																				set @exec_time = @var_time ;
																				set @exec_aiguid = @var_aiguid ;

																				EXECUTE exec using @exec_time,@exec_aiguid ;

																				DEALLOCATE  PREPARE exec ;*/
																				exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;
																			
																			end
																	END
															end 
													end
											END 
										
										set @var_refaiindex = @var_refaiindex+1;
									end
								set @pmsg = @pmsg+' ���³���ʵʱ����';
								/*#ɾ������ʵʱ��¼*/
								delete from fdapcarrealdata where refId = @var_refid;
								IF(@var_refalarmstatus = 0) 
									BEGIN
										insert into fdapcarrealdata(refId,aid1,aid2,aid3,t1,t2,t3,latitude,latitude_dir,longitude,longitude_dir,Time,isalarm) 
										values(@var_refid,@var_aid1,@var_aid2,@var_aid3,@t1_value,@t2_value,@t3_value,@var_latitude,@var_latitude_dir,@var_longitude,@var_longitude_dir,@var_time,0);
									END
								ELSE
									BEGIN
										insert into fdapcarrealdata(refId,aid1,aid2,aid3,t1,t2,t3,latitude,latitude_dir,longitude,longitude_dir,Time,isalarm) 
										values(@var_refid,@var_aid1,@var_aid2,@var_aid3,@t1_value,@t2_value,@t3_value,@var_latitude,@var_latitude_dir,@var_longitude,@var_longitude_dir,@var_time,1);
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
GO


/**
		���ʵʱ�����ϴδ洢����
		param : authCode
		param : {id,VALUE,time,status}
		return: 0 �ɹ� 1 ʧ��
		
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadRefRealData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadRefRealData]

**/
CREATE PROCEDURE [dbo].[proc_loadRefRealData] 
@pauthCode varchar(40),@psource varchar(4000),@Time varchar(25),@pisOk int output,@pmsg varchar(400) output,@alarmRefs varchar(400) output
AS
BEGIN
		/*�������*/
		declare @var_loopNumber int ;
		set @var_loopNumber = 1;
		declare @var_loopIndex int;
		set @var_loopIndex = 1;
		/*declare @var_hisdata_table varchar(50) default '' ;
		declare @var_hisalarm_table varchar(50) default '' ;*/

		declare @var_oid bigint;
		set @var_oid = -1;


		/*��������Ԥִ�е�sql���*/
		declare @var_addHisAlarm_sql nvarchar(400) 
		set @var_addHisAlarm_sql ='insert into @table (aiGUID,alarmData,startTime,alarmlevel,alarmtype,alarmstandard,flag) values (@In_aiGUID,@In_alarmData,@In_startTime,@In_alarmlevel,@In_alarmtype,@In_alarmstandard,0)' ;						/**������ʷ����ִ�е�sql���*/
		declare @var_updateHisAlarm_sql nvarchar(400) 
		set @var_updateHisAlarm_sql = 'update @table set endTime =@In_endTime ,flag = 1 where aiguid = @In_aiguid and flag = 0';						/**������ʷ����ִ�е�sql���*/
		declare @var_addHisdata_sql nvarchar(400)
		set @var_addHisdata_sql = 'insert into @table (aiGUID,data,IsAlarm,time) values (@In_aiGUID,@In_data,@In_IsAlarm,@In_time)' ;					/**������ʷ����ִ�е�sql���*/

		/*������ʷ���������ʷ���ݱ�*/
		DECLARE @var_histable varchar(30),@var_hisalarmtable varchar(30) ;
		

		
		/*��������*/
		BEGIN TRANSACTION ;
			BEGIN TRY
				set @pmsg = '';
				set @alarmRefs = '';
			
				set @var_loopNumber  = dbo.func_stringtotal(@psource,';') ;

				set @var_oid = dbo.func_get_oid(@pauthCode) ;
				
				/*��֤��ҵ�Ƿ�Ϸ�*/
				IF @var_oid =-1 or (@var_loopNumber is null) 
					BEGIN
						ROLLBACK TRANSACTION;
						set @pmsg = '��Ȩ��Ƿ��򴫵�����Ϊ��';
						set @pisOk = 1 ;
					END
				ELSE 
						BEGIN
							
							/*���ñ���*/
							set @var_histable = 'fdaprefhisdata_'+convert(varchar(20),@var_oid) ;
							set @var_hisalarmtable = 'fdaphisalarm_'+convert(varchar(20),@var_oid) ;
								
							
							
							/*����ִ�ж�̬���sql���*/
							set @var_addHisAlarm_sql =  replace(@var_addHisAlarm_sql,'@table',@var_hisalarmtable) ;
							set @var_updateHisAlarm_sql = replace(@var_updateHisAlarm_sql,'@table',@var_hisalarmtable) ;
							set @var_addHisdata_sql = replace(@var_addHisdata_sql,'@table',@var_histable) ;
							
							
						
							set @pmsg = '��ʼѭ��̽ͷ����' ;
							WHILE @var_loopIndex <= @var_loopNumber
								BEGIN
									DECLARE @var_reid int  ;
									DECLARE @var_value decimal(5,2) ;
									DECLARE	@var_time varchar(25) ;
									DECLARE @var_status int ;

									/*̽ͷ��guid��ʶ*/
									DECLARE @var_aiguid varchar(40) ;
									set @var_aiguid = '';
									/*̽ͷ�ı������ͣ��Լ���һ�εı�������*/
									DECLARE @var_alarmType int,@var_prealarmtype int ;
									/*̽ͷ�Ƿ��Ѿ���������*/
									DECLARE @var_isrealalarm int ;		
									/*̽ͷ�����Ƿ��Ѿ�������ʱ*/
									DECLARE @var_isOverDelay int ;
									/*̽ͷ����ʷ����״̬,Ĭ��Ϊ����*/
									DECLARE @var_aiHisAlarmStatus	int;
									set @var_aiHisAlarmStatus = 1;
									
									declare @var_alarmlevel int;
									set @var_alarmlevel = -1;
									declare @var_alarmstandard decimal(5,2);

									/*��������*/
									set @var_reid = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',1) ;
									set @var_value = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',2) ;
									set @var_status = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',3) ; 
									set @var_time = @Time;
									

									
									

									/*��֤��ǰ̽ͷ�Ƿ�Ϸ�*/
									set @var_aiguid = dbo.func_aiguid_byoid(@var_oid,@var_reid) ;
									
										if(@var_aiguid='') 
											begin
												/*̽ͷ��ʶ�޷���ȡ���ֶ���������*/
												set @pmsg = '�Ҳ�����ҵ��Ӧ��̽ͷ' ;
												select id from fdapuser5 ;
											end
										else 
											begin
												/*��ȡ��ǰ̽ͷ�ı���״̬*/
												set @var_alarmType = dbo.func_get_ai_alarmstatus(@var_aiguid,@var_value) ;
												set @var_prealarmtype = dbo.func_get_ai_prealarmstatus(@var_aiguid) ;
													/*�ж��Ƿ�Ϊ������*/
													if @var_status = 0 or @var_status = 1
														begin
															/*��ǰ̽ͷ����*/
															if @var_alarmType = 0 
																begin
																	/*������ʷ���ݱ���״̬*/
																	set @var_aiHisAlarmStatus = 1 ;

																	/*�ж�̽ͷ�Ļ�δ������*/
																	IF @var_prealarmtype = 0 
																		BEGIN
																			set @pmsg = '̽ͷһֱ����' ;
																		END
																	/*��ǰ̽ͷ�Ѿ�������*/
																	ELSE
																		BEGIN
																			set @var_isrealalarm = dbo.func_get_alarmIsRealAlarm(@var_aiguid) ;
																			/*�ж�֮ǰ�ı����Ƿ�Ϊ�����ı���*/
																			IF @var_isrealalarm = 0 
																				BEGIN
																					/*ɾ��ʵʱ�����еı���(δ����ʱ)��ʶ*/
																					DELETE from fdaprealalarm where  aiguid = @var_aiguid ;
																				END
																			ELSE
																				BEGIN
																					/*ɾ��ʵʱ�����еı���(������ʱ)��ʶ*/
																					DELETE from fdaprealalarm where  aiguid = @var_aiguid ;
																							
																					/*����ִ�и���*/
																					
																					exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;
																					
																				END	

																		END
																end
															/*��ǰ̽ͷ��������*/
															else 
																begin
																	/*������ʷ���ݱ���״̬*/
																	set @var_aiHisAlarmStatus = 1 ;

																	/*�ж�̽ͷ�Ļ�δ������*/														
																	IF @var_prealarmtype = 0 
																		BEGIN
																			set @pmsg = '̽ͷ��һ�α���' ;
																			/*�����µı�����¼*/
																			INSERT into fdaprealalarm (aiguid,orgid,alarmdata,time,isrealalarm,alarmlevel,alarmtype,alarmstandard)
																			VALUES (@var_aiguid,@var_oid,@var_value,@var_time,0,dbo.func_getAlarmLevel(@var_alarmType),@var_alarmType,dbo.func_get_alarmOverValue(@var_aiguid ,@var_value ,@var_alarmType));
																		END		
																	/*��ǰ̽ͷ�Ѿ�������*/
																	ELSE
																		BEGIN	
																			set @var_isrealalarm = dbo.func_get_alarmIsRealAlarm(@var_aiguid) ;

																			/*�жϵ�ǰ̽ͷ��ǰ�����α����������Ƿ�һ��*/
																			IF (@var_alarmType = @var_prealarmtype)
																				BEGIN
																					/*�ж��ǲ��������ı���(0δ��������,1��������)*/
																					IF @var_isrealalarm = 0
																						BEGIN
																							/*�жϵ�ǰ�����Ƿ񳬹���ʱ*/
																								
																							set @var_isOverDelay = dbo.func_get_IsOverAlarmDelay(@var_aiguid,@var_alarmType,@var_time) ;
																											
																							IF @var_isOverDelay = 0 
																								BEGIN
																									set @pmsg =  'δ����ʱ����������' ;
																								END
																							ELSE
																								BEGIN
																									set @pmsg = '������ʱ,������ʷ����' ;

																									set @alarmRefs = @alarmRefs+convert(varchar(20),dbo.func_get_refid_byaiguid(@var_aiguid))+',';
																									/*������ʷ���ݱ���״̬*/
																									set @var_aiHisAlarmStatus = 0 ;

																									/*����ʱ������ʵʱ�������ݡ������ʷ��������	*/
																									update fdaprealalarm set isrealalarm =1 ,alarmdata = @var_value ,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@var_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 0 ;

																									
																									set @var_alarmlevel = dbo.func_getAlarmLevel(@var_alarmType);
																									set @var_alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid ,@var_value,@var_alarmType);
																									exec sp_executesql @var_addHisAlarm_sql,
																										N'@In_aiGUID varchar(40),@In_alarmData decimal(5,2),@In_startTime varchar(25),@In_alarmlevel int,@In_alarmtype int,@In_alarmstandard decimal(5,2)',
																										@var_aiguid,@var_value,@var_time,@var_alarmlevel,@var_alarmType,@var_alarmstandard;
																				
																								END 
																						END
																					/*�����ı���*/
																					ELSE
																						BEGIN
																							set @pmsg =  '����������ʱ,��������' ;
																							/*������ʷ���ݱ���״̬*/
																							set @var_aiHisAlarmStatus = 0 ;
																							update fdaprealalarm set alarmdata = @var_value,alarmstandard = dbo.func_get_alarmOverValue(@var_aiguid,@var_value,@var_alarmType) where aiguid = @var_aiguid and isrealalarm = 1 ;
																												
																						END 
																				END			
																			/*���α�������ǰ��һ��*/
																			ELSE
																				BEGIN
																					set @pmsg = 'ǰ�����α������Ͳ�ͬ' ;
																					/*�ж��ǲ��������ı��� (0δ�������� ��1 ��������)
																					֮ǰ��δ��������,ɾ��ԭ�ȵļ�¼�����²���һ���µı�����ʶ*/
																					DELETE from fdaprealalarm where aiguid = @var_aiguid  ;

																					INSERT INTO fdaprealalarm (aiguid,orgid,alarmdata,time,isrealalarm,
																						alarmlevel,alarmtype,alarmstandard) VALUES (@var_aiguid,@var_oid,@var_value,@var_time,0,dbo.func_getAlarmLevel(@var_alarmType),@var_alarmType,dbo.func_get_alarmOverValue(@var_aiguid ,@var_value ,@var_alarmType));
																													
																					IF @var_isrealalarm = 1 
																						BEGIN																						
																							/*����ʵʱ���������ݺͱ������ͣ������ϴε���ʷ��������*/
																							/*����ִ�и���*/
																							
																							exec sp_executesql @var_updateHisAlarm_sql,N'@In_endTime varchar(25),@In_aiguid varchar(40)',@var_time,@var_aiguid;
																																												
																						END
																				END
																				
																		END	
																end 
																				
															/*ɾ�����̽ͷ��ʵʱ����*/
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
															
												/*�������״̬Ϊ����0 ,������ʷ����*/
												if (@var_status = 0 or @var_status = 2)
													begin		
																
														exec sp_executesql @var_addHisdata_sql,
															N'@In_aiGUID varchar(40),@In_data decimal(5,2),@In_IsAlarm int,@In_time varchar(25)',
															@var_aiguid,@var_value,@var_aiHisAlarmStatus,@var_time;
															
																			
													end;
											end	/*�������ж�̽ͷ�Ƿ�Ϸ�*/
										
									set @var_loopIndex = @var_loopIndex + 1 ;
								END ;
							
							 set @pisOk = 0 ;
							COMMIT TRANSACTION;
						END ;
			END TRY
			/*������������*/
				/****/
			BEGIN CATCH
					rollback TRANSACTION;						/*���۷���ʲô���󶼻ع�����*/
					set @pisOk = 1 ;							/*�����������״̬��ʧ��*/
					set @pmsg = @pmsg+'�����ع�����������' ;
			END CATCH
END ;
GO

/**
		������ͣ��¼�ϴ��洢����
		param : authCode
		param : @psource {aid1,aid2,aid3,startTime,endTime,carrier,IntervalValue}
		return: 0 �ɹ� 1 ʧ��
		

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadStartupData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadStartupData]
**/
CREATE procedure [dbo].[proc_loadStartupData]
 @pauthCode varchar(40), @psource varchar(4000),@pisOk int output,@pmsg varchar(400) output
AS
BEGIN
	declare @var_loopnumber int,@var_loopIndex int;
	set @var_loopnumber = 1;
	set @var_loopIndex = 1;
	declare @var_oid BIGINT;
	set @var_oid = -1;
	DECLARE @var_startuptable VARCHAR(50) ;
	set @var_startuptable = '';
	
	declare @var_startup_sql NVARCHAR(400)
	set @var_startup_sql ='insert into @table (refid,startTime,carrier,IntervalValue) values(@In_var_refid,@In_var_startTime,@In_var_carrier,@In_var_IntervalValue)';

	declare @var_startup_sql_end NVARCHAR(400)
	set @var_startup_sql_end ='update @table set endTime=@In_var_endTime where refid = @In_var_refid and startTime = @In_var_startTime';

	declare @var_startup_existStart NVARCHAR(400)
	set @var_startup_existStart ='select @out_startupid=startupid from @table where refid = @In_var_refid and startTime = @In_var_startTime';

	declare @var_startup_sql_all NVARCHAR(400)
	set @var_startup_sql_all ='insert into @table (refid,startTime,endTime,carrier,IntervalValue) values(@In_var_refid,@In_var_startTime,@In_var_endTime,@In_var_carrier,@In_var_IntervalValue)';


	declare @var_startupid bigint;

	begin TRANSACTION ;
		begin try
			
			set @pmsg = '';

			set @var_loopnumber = dbo.func_stringtotal(@psource,';');
			set @var_oid = dbo.func_get_oid(@pauthCode);
			if (@var_oid = -1) or (@var_loopnumber is null)
				begin
					ROLLBACK TRANSACTION
					set @pisOk = 1;
					set @pmsg = @pmsg+'��Ȩ��Ƿ��򴫵�����Ϊ��';
				end
			ELSE
					begin
						set @var_startuptable = 'Fdapstartup_'+convert(varchar(20),@var_oid);
						set @var_startup_sql = replace(@var_startup_sql,'@table',@var_startuptable);
						set @var_startup_sql_end = replace(@var_startup_sql_end,'@table',@var_startuptable);
						set @var_startup_existStart = replace(@var_startup_existStart,'@table',@var_startuptable);
						set @var_startup_sql_all = replace(@var_startup_sql_all,'@table',@var_startuptable);
						/*set @pmsg = @pmsg+'�����������';*/
						
						while @var_loopIndex<=@var_loopnumber
							BEGIN
									declare @var_aid1 int ;
									set @var_aid1 = -1;
									declare @var_refid BIGINT ;
									set @var_refid = -1;
									declare @var_startTime VARCHAR(25),@var_endTime VARCHAR(25);
									set @var_startTime = '';
									set @var_endTime = '';
									declare @var_carrier VARCHAR(30) ;
									set @var_carrier = '';
									declare @var_IntervalValue int ;
									set @var_IntervalValue = 0;
									declare @var_aiguid1 varchar(40) ;
									set @var_aiguid1 = '';
									
									set @var_aid1 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',1);
									set @var_startTime = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',4);
									set @var_endTime = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',5);
									set @var_carrier = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',6);
									set @var_IntervalValue = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',7);
									
									set @var_aiguid1 = dbo.func_aiguid_byoid(@var_oid,@var_aid1);
									set @var_refid = dbo.func_get_refid_byaiguid(@var_aiguid1);
									
									if(@var_aid1 = -1 or @var_refid = -1 or @var_aiguid1 = '') 
										begin
											set @pmsg = @pmsg+'�Ҳ�����ҵ��Ӧ��̽ͷ';
											select id from Fdapuser5;
										end
									else
										begin
											/*declare @startupSql nvarchar(400)
											set @startupSql = @var_startup_sql+'('+convert(varchar(20),@var_refid)+','+@var_startTime+','+@var_endTime+','+@var_carrier+','+convert(varchar(20),@var_IntervalValue)+')';
											PREPARE exec from @startupSql ;

											set @exec_refid = @var_refid;
											set @exec_startTime = @var_startTime;
											set @exec_endTime = @var_endTime;
											set @exec_carrier = @var_carrier;
											set @exec_IntervalValue = @var_IntervalValue;

											EXECUTE exec USING @exec_refid,@exec_startTime,@exec_endTime,@exec_carrier,@exec_IntervalValue;
											DEALLOCATE  PREPARE exec ;*/
											set @pmsg =@pmsg+'1';
											if(@var_endTime is null or @var_endTime='')
												begin
													exec sp_executesql @var_startup_sql,N'@In_var_refid int,@In_var_startTime varchar(25),@In_var_carrier varchar(30),@In_var_IntervalValue int',@var_refid,@var_startTime,@var_carrier,@var_IntervalValue;
												end
											else
												begin
													set @var_startupid = -1;
													exec sp_executesql @var_startup_existStart,N'@In_var_refid int,@In_var_startTime varchar(25),@out_startupid bigint output',@var_refid,@var_startTime,@var_startupid output;
													if(@var_startupid is null or @var_startupid=-1)
														begin
															exec sp_executesql @var_startup_sql_all,N'@In_var_refid int,@In_var_startTime varchar(25),@In_var_endTime varchar(25),@In_var_carrier varchar(30),@In_var_IntervalValue int',@var_refid,@var_startTime,@var_endTime,@var_carrier,@var_IntervalValue;
														end
													else
														begin
															exec sp_executesql @var_startup_sql_end,N'@In_var_endTime varchar(25),@In_var_refid int,@In_var_startTime varchar(25)',@var_endTime,@var_refid,@var_startTime;
														end
												end
										end;
									set @var_loopIndex = @var_loopIndex+1;
							END;
					end
			set @pisOk = 0;
			print @pmsg;
			COMMIT TRANSACTION;
		end try
		begin catch
			rollback TRANSACTION;
			set @pisOk = 1 ;
			set @pmsg =@pmsg+ '�����ع�����������'+convert(varchar(20),@var_oid)+convert(varchar(20),@var_loopnumber)+@var_startup_sql+@var_startup_sql_all ;
		end catch
END
GO

