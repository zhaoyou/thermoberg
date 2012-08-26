set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER  FUNCTION [dbo].[func_stringtotal](@f_string varchar(1000),@f_delimiter varchar(5))
RETURNS int
BEGIN
  -- Get the total number of given string.
  return 1+(len(@f_string) - len(replace(@f_string,@f_delimiter,'')));
END ;
