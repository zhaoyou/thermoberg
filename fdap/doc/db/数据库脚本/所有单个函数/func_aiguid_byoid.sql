set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER function [dbo].[func_aiguid_byoid](@poid BIGINT ,@preid int)
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
