USE [tbcc_fdap]
GO
set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

CREATE function [dbo].[func_aiguid_byoid](@poid BIGINT ,@preid int)
RETURNS varchar(40)
BEGIN
		DECLARE @var_aiguid VARCHAR(40) ;
		set @var_aiguid = ''
		select  @var_aiguid = ai.aiguid from fdapaiinfo as ai 
						INNER join fdapref ref on ai.refId = ref.refId 
						INNER join fdapproject p on p.projectid = ref.projectid
						INNER join fdaporg org on p.oid = org.oid
			where org.oid = @poid and ai.reid = @preid ;
		return @var_aiguid ;
END
GO

CREATE function [dbo].[func_get_ai_alarmstatus](@paiguid varchar(40),@pvalue decimal(5,2))
RETURNS int
BEGIN
		declare @var_status int, @var_selftype  int ;
		set @var_status = 0;
		set @var_selftype = 0;
		declare @var_lower decimal (5,2), @var_high decimal (5,2) ;

		if @pvalue<>-300 begin
			select @var_lower=lowerlimit , @var_high=highlimit ,@var_selftype=selftype  from fdapaiinfo where aiguid = @paiguid ;

		/**根据探头类型，判断报警状态*/
			if @var_selftype = 0 begin
				if @pvalue <= @var_lower 
						set @var_status = 1 ;
				else 
					begin
						if @pvalue >= @var_high
						set @var_status = 2 ;
					end;
				end;
			else 
				begin
					if @pvalue <= @var_lower 
							set @var_status = 3 ;
					else 
						begin
							if @pvalue >= @var_high
							set @var_status = 4 ;
						end;
				END ;
		end;
		return @var_status ;			
end 
GO


CREATE function [dbo].[func_get_ai_prealarmstatus] (@paiguid varchar(40))
returns int
BEGIN
			DECLARE @var_flag int ;
			set @var_flag = 0;
			select @var_flag=alarmtype  from fdaprealalarm where aiguid = @paiguid  ;
			return @var_flag ;
END ;
GO

CREATE function [dbo].[func_get_alarmIsRealAlarm](@paiguid varchar(40))
returns INT
BEGIN
		DECLARE @var_flag int ;
		set @var_flag = 0;
		select @var_flag=isrealalarm from fdaprealalarm where aiguid = @paiguid ;
		return @var_flag ;
END ;
GO


CREATE function [dbo].[func_get_alarmOverValue](@paiguid varchar(40),@pvalue decimal(5,2),@palarmtype int)
RETURNS decimal(5,2)
BEGIN
	DECLARE @var_lower DECIMAL(5,2),@var_high DECIMAL(5,2);
	select @var_lower=lowerlimit ,@var_high=highlimit from fdapaiinfo where aiguid = @paiguid ;
	
	/*根据报警类型的上下限，获取报警超过的值*/
	IF (@palarmtype=1 or @palarmtype = 3)
			return ABS(@var_lower-@pvalue	) ;
	return ABS(@var_high-@pvalue) ;
	
END
GO


CREATE function [dbo].[func_get_alarmStatusissame](@paiguid varchar(40),@palarmtype int)
returns INT
BEGIN
			declare @var_current_alarm int  ;
			select @var_current_alarm=alarmtype  from fdaprealalarm where aiguid = @paiguid;
			if  @palarmtype =@var_current_alarm 	
					return 0 
			return 1 
			
END 
GO

CREATE function [dbo].[func_get_carhis_IsOverAlarmDelay](@paiguid varchar(40),@ptype int ,@var_alarmTime datetime,@ptime datetime)
RETURNS INT
BEGIN
			declare @var_delay int;
			set @var_delay = 0;
		
			/**根据类型判断,获取上限还是下限**/

			if (@ptype=1 or @ptype=3)
					select @var_delay=lowerdelay  from fdapaiinfo where aiguid = @paiguid ;
			ELSE	
					select @var_delay=highdelay  from fdapaiinfo where  aiguid = @paiguid ;
			

			if DATEDIFF(SECOND,DATEADD(SECOND,@var_delay*60,@var_alarmTime),@ptime)>0
				return 1  ;
			return 0 ;

END ;
GO


CREATE function [dbo].[func_get_IsOverAlarmDelay](@paiguid varchar(40),@ptype int ,@ptime datetime)
RETURNS INT
BEGIN
			declare @var_delay int;
			set @var_delay = 0;
			declare @var_alarmTime datetime ;
		
			/**根据类型判断,获取上限还是下限**/

			if (@ptype=1 or @ptype=3)
					select @var_delay=lowerdelay  from fdapaiinfo where aiguid = @paiguid ;
			ELSE	
					select @var_delay=highdelay  from fdapaiinfo where  aiguid = @paiguid ;
			


			select  @var_alarmTime=fdaprealalarm.Time  from fdaprealalarm where aiguid = @paiguid ;


			if DATEDIFF(SECOND,DATEADD(SECOND,@var_delay*60,@var_alarmTime),@ptime)>0
				return 1  ;
			return 0 ;

END ;
GO


CREATE function [dbo].[func_get_oid](@pcode varchar(40))
returns BIGINT
BEGIN
	DECLARE @orgid BIGINT ;
	set @orgid = -1;
	select @orgid = oid from dbo.fdapauthcode where code = @pcode ;
	return @orgid ;
END
GO


CREATE function [dbo].[func_get_refid_byaiguid](@paiguid varchar(40))
returns bigint
begin
	declare @prefid bigint;
	set @prefid = -1
	select @prefid=refId from fdapaiinfo where aiguid = @paiguid;
	return @prefid;
end;
GO


CREATE  FUNCTION [dbo].[func_get_split_string](
@f_string varchar(1000) ,@f_delimiter varchar(5),@f_order int) 
RETURNS varchar(1000)
BEGIN
  -- Get the separated number of given string.
  declare @result varchar(1000) 
	set @result = @f_string
	declare @i int, @start int,@end int
	set @i = 0;
	set @start = 0;
	set @end = 0;
	while @i<(@f_order-2)
	begin
		set @result = stuff(@result,charindex(@f_delimiter,@result),1,'$');
		set @i=@i+1
	end
	if @f_order >1
		begin
			set @start = charindex(@f_delimiter,@result);
			set @result = stuff(@result,charindex(@f_delimiter,@result),1,'$');
			set @end = charindex(@f_delimiter,@result);
		end
	else
		begin
			set @end = charindex(@f_delimiter,@result);
		end
	if @end>0
		set @result = substring(@result,@start+1,@end-@start-1);
	else
		set @result = substring(@result,@start+1,len(@result));
  return @result;
END ;
GO


/**
			获取报警的严重级别
			全部报警初步定义一般报警
			温度 1 严重报警
			湿度 2 一般报警
**/
CREATE function [dbo].[func_getAlarmLevel](@palarmtype int)
returns INT
BEGIN
		/*if(@palarmtype=1 or @palarmtype= 2)
				return 1 ;
		return 2 ;
		*/
		return 2 ;
END ;
GO


CREATE  FUNCTION [dbo].[func_stringtotal](@f_string varchar(1000),@f_delimiter varchar(5))
RETURNS int
BEGIN
  -- Get the total number of given string.
  return 1+(len(@f_string) - len(replace(@f_string,@f_delimiter,'')));
END ;
GO


