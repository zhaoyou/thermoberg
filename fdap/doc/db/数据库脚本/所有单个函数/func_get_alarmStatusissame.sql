set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER function [dbo].[func_get_alarmStatusissame](@paiguid varchar(40),@palarmtype int)
returns INT
BEGIN
			declare @var_current_alarm int  ;
			select @var_current_alarm=alarmtype  from fdaprealalarm where aiguid = @paiguid;
			if  @palarmtype =@var_current_alarm 	
					return 0 
			return 1 
			
END 
