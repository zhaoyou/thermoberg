set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go








/**
		车载历史数据上传存储过程
		param : @pauthcode
		param : @psource {aid1,aid2,aid3,t1,t2,t3,latitude,Latitude_dir,longitude,longitude_dir,`time`,isalarm}
		return: 0 成功 1 失败
		

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadCarHisData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadCarHisData]
**/

ALTER PROCEDURE [dbo].[proc_loadCarHisData]
 @pauthcode VARCHAR(40),@psource VARCHAR(4000),@pisok INT output,@pmsg VARCHAR(400) output,@dealhisMsg VARCHAR(1000) output
AS
BEGIN
		/*定义变量*/
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
		
		/*开启事务*/
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
					set @pmsg = @pmsg+'授权码非法或传递数据为空';
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
											set @pmsg = '找不到企业对应的探头';
											select id from Fdapuser5;
										end
									else
										begin
											set @var_startupid = -1;
											/*set @pmsg = @pmsg+'查找历史数据对应的启停记录id'+convert(varchar(20),@var_startupid);*/
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
													set @pmsg = '未找到对应的启停记录';
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

		/*定义错误处理程序*/
		/****/
		begin catch
			rollback TRANSACTION;										/*无论发生什么错误都回滚事务*/
			set @pisok = 1 ; 							/*代表操作操作状态码失败*/
			set @pmsg =@pmsg+ '操作回滚，发生错误' ;
		end catch;	
END;







