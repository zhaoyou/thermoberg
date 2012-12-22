set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go






/**
		小批零历史数据上传存储过程
		param : @pauthcode
		param : @psource {aid1,t1,latitude,Latitude_dir,longitude,longitude_dir,`time`,isalarm}
		return: 0 成功 1 失败
		

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadretailHisData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadretailHisData]
**/

ALTER PROCEDURE [dbo].[proc_loadRetailHisData]
 @pauthcode VARCHAR(40),@psource VARCHAR(4000),@pisok INT output,@pmsg VARCHAR(400) output,@dealhisMsg VARCHAR(1000) output
AS
BEGIN
		/*定义变量*/
		DECLARE @var_loopnumber int,@var_loopIndex INT;
		set @var_loopnumber = 1;
		set @var_loopIndex =1;
		DECLARE @var_oid BIGINT;
		set @var_oid = -1;
		DECLARE @var_retailhisdataTable VARCHAR(50);
		set @var_retailhisdataTable = '';

		DECLARE @var_retailhisdata_sql NVARCHAR(400)
		set @var_retailhisdata_sql= 'insert into @table (T1,StartupId,latitude,Latitude_dir,longitude,longitude_dir,time,isalarm) values(@In_t1,@In_StartupId,@In_latitude,@In_latitude_dir,@In_longitude,@In_longitude_dir,@In_time,@In_isalarm)';

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

					set @var_retailhisdataTable = 'Fdapretailhisdata_'+convert(varchar(20),@var_oid);
					set @var_retailhisdata_sql = replace(@var_retailhisdata_sql,'@table',@var_retailhisdataTable);
					set @var_startup_table = 'Fdapretailstartup_'+convert(varchar(20),@var_oid);
					set @var_startup_sql = replace(@var_startup_sql,'@table',@var_startup_table);

					WHILE @var_loopIndex<=@var_loopnumber
							BEGIN
									DECLARE @var_aid1 int;
									set @var_aid1=-1;
									
									DECLARE @var_t1 DECIMAL(5,2);

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
									DECLARE @var_aiguid1 VARCHAR(40);
									set @var_aiguid1 = '';

									declare @var_startupid bigint;


									set @var_aid1 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',1);
									
									set @var_t1 = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',2);
									

									set @var_latitude = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',3);
									set @var_latitude_dir = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',4);
									set @var_longitude = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',5);
									set @var_longitude_dir = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',6);
									set @var_time = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',7);

									set @var_isalarm = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',8);

									set @var_aiguid1 = dbo.func_aiguid_byoid(@var_oid,@var_aid1);
									

									set @var_refid = dbo.func_get_refid_byaiguid(@var_aiguid1);
									if(@var_aid1 = -1 ) 
										begin
											set @pmsg = '找不到企业对应的探头';
											select id from Fdapuser5;
										end
									else
										begin
											set @var_startupid = -1;
											
											exec sp_executesql @var_startup_sql,N'@In_refid int,@In_startTime varchar(25),@out_startupid bigint output',@var_refid,@var_time,@var_startupid output

											if(@var_startupid is null or @var_startupid = -1)
												begin
													rollback TRANSACTION;
													set @pisok =1;
													set @pmsg = '未找到对应的启停记录';
												end
											ELSE
												BEGIN
													
													set @dealhisMsg =@dealhisMsg+convert(varchar(20),@var_aid1)+','+convert(varchar(20),@var_startupid)+';';

													exec sp_executesql @var_retailhisdata_sql,
														N'@In_t1 DECIMAL(5,2),@In_StartupId bigint,@In_latitude DECIMAL(8,4),@In_latitude_dir int,@In_longitude DECIMAL(9,4),@In_longitude_dir int,@In_time VARCHAR(25),@In_isalarm int',
														@var_t1,@var_startupid,@var_latitude,@var_latitude_dir,@var_longitude,@var_longitude_dir,@var_time,@var_isalarm;

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



