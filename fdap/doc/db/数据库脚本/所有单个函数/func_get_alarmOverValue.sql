set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER function [dbo].[func_get_alarmOverValue](@paiguid varchar(40),@pvalue decimal(5,2),@palarmtype int)
RETURNS decimal(5,2)
BEGIN
	DECLARE @var_lower DECIMAL(5,2),@var_high DECIMAL(5,2);
	select @var_lower=lowerlimit ,@var_high=highlimit from fdapaiinfo where aiguid = @paiguid ;
	
	/*根据报警类型的上下限，获取报警超过的值*/
	IF (@palarmtype=1 or @palarmtype = 3)
			return ABS(@var_lower-@pvalue	) ;
	return ABS(@var_high-@pvalue) ;
	
END
