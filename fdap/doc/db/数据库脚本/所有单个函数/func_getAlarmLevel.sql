set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

/**
			获取报警的严重级别
			全部报警初步定义一般报警
			温度 1 严重报警
			湿度 2 一般报警
**/
ALTER function [dbo].[func_getAlarmLevel](@palarmtype int)
returns INT
BEGIN
		/*if(@palarmtype=1 or @palarmtype= 2)
				return 1 ;
		return 2 ;
		*/
		return 2 ;
END ;
