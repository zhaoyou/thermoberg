set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER function [dbo].[func_get_ai_prealarmstatus] (@paiguid varchar(40))
returns int
BEGIN
			DECLARE @var_flag int ;
			set @var_flag = 0;
			select @var_flag=alarmtype  from fdaprealalarm where aiguid = @paiguid  ;
			return @var_flag ;
END ;

