--Employees Earning More Than Their Managers
--https://leetcode.com/problems/employees-earning-more-than-their-managers/
select e.Name Employee from Employee e inner join Employee m on e.ManagerId=m.Id where m.Salary<e.Salary;