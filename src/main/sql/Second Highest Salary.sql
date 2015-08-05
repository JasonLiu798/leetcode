--Second Highest Salary
--https://leetcode.com/problems/second-highest-salary/


select ifnull(max(tmp.Salary),null) as SecondHighestSalary from (
select distinct Salary from Employee order by Salary desc limit 1,1
) tmp;