--Duplicate Emails
--https://com.jason.leetcode.com/problems/duplicate-emails/

select distinct p1.Email from Person p1,Person p2 where p1.Id!=p2.Id and p1.Email=p2.Email



