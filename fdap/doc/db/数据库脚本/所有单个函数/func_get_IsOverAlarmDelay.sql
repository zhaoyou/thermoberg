set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER function [dbo].[func_get_IsOverAlarmDelay](@paiguid varchar(40),@ptype int ,@ptime datetime)
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
