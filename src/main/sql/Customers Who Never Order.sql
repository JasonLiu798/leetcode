--Customers Who Never Order
--https://leetcode.com/problems/customers-who-never-order/

select c.Name Customers from Customers c left join Orders o on c.Id=o.CustomerId  where o.Id is null
