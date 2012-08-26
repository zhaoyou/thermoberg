set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go

ALTER  FUNCTION [dbo].[func_get_split_string](
@f_string varchar(1000) ,@f_delimiter varchar(5),@f_order int) 
RETURNS varchar(1000)
BEGIN
  -- Get the separated number of given string.
  declare @result varchar(1000) 
	set @result = @f_string
	declare @i int, @start int,@end int
	set @i = 0;
	set @start = 0;
	set @end = 0;
	while @i<(@f_order-2)
	begin
		set @result = stuff(@result,charindex(@f_delimiter,@result),1,'$');
		set @i=@i+1
	end
	if @f_order >1
		begin
			set @start = charindex(@f_delimiter,@result);
			set @result = stuff(@result,charindex(@f_delimiter,@result),1,'$');
			set @end = charindex(@f_delimiter,@result);
		end
	else
		begin
			set @end = charindex(@f_delimiter,@result);
		end
	if @end>0
		set @result = substring(@result,@start+1,@end-@start-1);
	else
		set @result = substring(@result,@start+1,len(@result));
  return @result;
END ;
