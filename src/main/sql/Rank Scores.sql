--Rank Scores
--https://leetcode.com/problems/rank-scores/

select so.Score,q3.rank from
(
  select q1.Score,(@rowNum:=@rowNum+1) rank from
  (
    select si.Score
      from Scores si group by si.Score order by si.Score desc
  ) q1,
  (Select (@rowNum :=0 ) ) q2
) q3 join Scores so where so.Score=q3.Score order by Score desc;