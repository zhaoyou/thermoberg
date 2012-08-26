set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER function [dbo].[func_get_oid](@pcode varchar(40))
returns BIGINT
BEGIN
	DECLARE @orgid BIGINT ;
	set @orgid = -1;
	select @orgid = oid from dbo.fdapauthcode where code = @pcode ;
	return @orgid ;
END
