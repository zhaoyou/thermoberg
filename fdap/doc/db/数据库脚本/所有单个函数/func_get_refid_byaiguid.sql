set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER function [dbo].[func_get_refid_byaiguid](@paiguid varchar(40))
returns bigint
begin
	declare @prefid bigint;
	set @prefid = -1
	select @prefid=refId from fdapaiinfo where aiguid = @paiguid;
	return @prefid;
end;
