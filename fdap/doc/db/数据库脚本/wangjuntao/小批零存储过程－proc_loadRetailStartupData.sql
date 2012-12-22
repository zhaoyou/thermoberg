set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go




/**
		车载启停记录上传存储过程
		param : authCode
		param : @psource {aid1,startTime,endTime,carrier,IntervalValue}
		return: 0 成功 1 失败
		

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_loadStartupData]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_loadStartupData]
**/
ALTER procedure [dbo].[proc_loadRetailStartupData]
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
					set @pmsg = @pmsg+'授权码非法或传递数据为空';
				end
			ELSE
					begin
						set @var_startuptable = 'Fdapretailstartup_'+convert(varchar(20),@var_oid);
						set @var_startup_sql = replace(@var_startup_sql,'@table',@var_startuptable);
						set @var_startup_sql_end = replace(@var_startup_sql_end,'@table',@var_startuptable);
						set @var_startup_existStart = replace(@var_startup_existStart,'@table',@var_startuptable);
						set @var_startup_sql_all = replace(@var_startup_sql_all,'@table',@var_startuptable);
						/*set @pmsg = @pmsg+'设置完表名后';*/

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
									set @var_startTime = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',2);
									set @var_endTime = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',3);
									set @var_carrier = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',4);
									set @var_IntervalValue = dbo.func_get_split_string(dbo.func_get_split_string(@psource,';',@var_loopIndex),',',5);

									set @var_aiguid1 = dbo.func_aiguid_byoid(@var_oid,@var_aid1);
									set @var_refid = dbo.func_get_refid_byaiguid(@var_aiguid1);

									if(@var_aid1 = -1 or @var_refid = -1 or @var_aiguid1 = '') 
										begin
											set @pmsg = @pmsg+'找不到企业对应的探头';
											select id from Fdapuser5;
										end
									else
										begin
											
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
			set @pmsg =@pmsg+ '操作回滚，发生错误'+convert(varchar(20),@var_oid)+convert(varchar(20),@var_loopnumber)+@var_startup_sql+@var_startup_sql_all ;
		end catch
END



