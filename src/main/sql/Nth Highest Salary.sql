--Nth Highest Salary
--https://leetcode.com/problems/nth-highest-salary/
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  DECLARE i_cnt INT;
	#Routine body goes here...
	SET i_cnt = `N`-1;
	select max(tmp.Salary) into @result from (
		select distinct Salary from Employee order by Salary desc limit i_cnt,1
	) tmp;
	RETURN @result;
END