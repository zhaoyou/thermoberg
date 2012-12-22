set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go





/**
		处理小批零历史数据报警标识位存储过程
		param : @source			{oid,aid1,aid2,aid3,startupid}
		return: 0 成功 1 失败
		
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[proc_dealretailhis]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[proc_dealretailhis]

**/
create PROCEDURE [dbo].[proc_dealRetailhis]
@source varchar(50),@pis_ok int output
AS
BEGIN
	/*开启事务*/
	begin TRANSACTION ;
	begin try
		declare @var_time varchar(25),@alarm_time1 varchar(25),@alarm_time2 varchar(25),@alarm_time3 varchar(25),@var_t1 decimal(5,2),@var_t2 decimal(5,2),@var_t3 decimal(5,2),@var_isalarm int;

		declare @var_aiguid1 varchar(36),@var_aiguid2 varchar(36),@var_aiguid3 varchar(36);

		declare @var_id bigint,@var_oid bigint,@startupid bigint;

		declare @var_alarmstatus1 int,@var_alarmstatus2 int,@var_alarmstatus3 int;

		declare @var_overDelay1 int,@var_overDelay2 int,@var_overDelay3 int;

		declare @sql NVARCHAR(400),@up_sql NVARCHAR(400),@var_retailhisdataTable Varchar(50);

		set @var_oid = dbo.func_get_split_string(@source,',',1);
		set @startupid = dbo.func_get_split_string(@source,',',5);

		set @var_aiguid1 = dbo.func_aiguid_byoid(@var_oid,dbo.func_get_split_string(@source,',',2));
		set @var_aiguid2 = dbo.func_aiguid_byoid(@var_oid,dbo.func_get_split_string(@source,',',3));
		set @var_aiguid3 = dbo.func_aiguid_byoid(@var_oid,dbo.func_get_split_string(@source,',',4));

		set @var_retailhisdataTable = 'fdapretailhisdata_'+convert(varchar(20),@var_oid);

		/*print @var_aiguid1+' '+@var_aiguid2+' '+@var_aiguid3;*/
		set @sql = 'select id,t1,t2,t3,isalarm,time from '+@var_retailhisdataTable+' where startupid = '+convert(varchar(10),@startupid)+' order by time';


		declare @tables_t TABLE(id bigint,t1 decimal(5,2),t2 decimal(5,2),t3 decimal(5,2),isalarm int,time varchar(25));
		insert into @tables_t exec sp_executesql @sql;


		declare retail_hisdata_cursor CURSOR for
		select id,t1,t2,t3,isalarm,time from @tables_t;

		open retail_hisdata_cursor

		FETCH NEXT from retail_hisdata_cursor
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
					set @var_overDelay1 = dbo.func_get_retailhis_IsOverAlarmDelay(@var_aiguid1,@var_alarmstatus1,@alarm_time1,@var_time);
				end

			if @var_alarmstatus2=0 begin
					set @alarm_time2 = '';
					set @var_overDelay1=0;
				end
			else begin
					if @alarm_time2='' begin
							set @alarm_time2=@var_time;
						end
					set @var_overDelay2 = dbo.func_get_retailhis_IsOverAlarmDelay(@var_aiguid2,@var_alarmstatus2,@alarm_time2,@var_time);
				end

			if @var_alarmstatus3=0 begin
					set @alarm_time3 = '';
					set @var_overDelay1=0;
				end
			else begin
					if @alarm_time3='' begin
							set @alarm_time3=@var_time;
						end
					set @var_overDelay3 = dbo.func_get_retailhis_IsOverAlarmDelay(@var_aiguid3,@var_alarmstatus3,@alarm_time3,@var_time);
				end


			if @var_isalarm <>0 and @var_isalarm<>1 begin
					if @var_overDelay1=1 or @var_overDelay2=1 or @var_overDelay3=1 begin
							set @up_sql = 'update '+@var_retailhisdataTable+' set isalarm = 0 where id ='+convert(varchar(25),@var_id);
							exec sp_executesql @up_sql;
						end
					else begin
							set @up_sql = 'update '+@var_retailhisdataTable+' set isalarm = 1 where id ='+convert(varchar(25),@var_id);
							exec sp_executesql @up_sql;
						end
				end

			FETCH NEXT from retail_hisdata_cursor 
			INTO @var_id,@var_t1,@var_t2,@var_t3,@var_isalarm,@var_time
		end

		close retail_hisdata_cursor
		deallocate retail_hisdata_cursor;

		set @pis_ok = 0;
		COMMIT TRANSACTION;
	end try

	/*定义错误处理程序*/
	/****/
	begin catch
		rollback TRANSACTION;										/*无论发生什么错误都回滚事务*/
		set @pis_ok = 1 ; 							/*代表操作操作状态码失败*/
	end catch;	
end;

