set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER function [dbo].[func_get_ai_alarmstatus](@paiguid varchar(40),@pvalue decimal(5,2))
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
