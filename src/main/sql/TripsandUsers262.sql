-- 262 hard
-- Trips and Users
-- https://leetcode.com/problems/trips-and-users/
-- ac Runtime: 271 ms
DROP TABLE IF EXISTS `Trips`;
CREATE TABLE `Trips` (
  `Id` int(11) DEFAULT NULL,
  `Client_Id` int(11) DEFAULT NULL,
  `Driver_Id` int(11) DEFAULT NULL,
  `City_Id` int(11) DEFAULT NULL,
  `Status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Request_at` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of Trips
-- ----------------------------
INSERT INTO `Trips` VALUES ('1', '1', '10', '1', 'completed', '2013-10-01');
INSERT INTO `Trips` VALUES ('2', '2', '11', '1', 'cancelled_by_driver', '2013-10-01');
INSERT INTO `Trips` VALUES ('3', '3', '12', '6', 'completed', '2013-10-01');
INSERT INTO `Trips` VALUES ('4', '4', '13', '6', 'cancelled_by_driver', '2013-10-01');
INSERT INTO `Trips` VALUES ('5', '1', '10', '1', 'completed', '2013-10-02');
INSERT INTO `Trips` VALUES ('6', '2', '11', '6', 'completed', '2013-10-02');
INSERT INTO `Trips` VALUES ('7', '3', '12', '6', 'completed', '2013-10-02');
INSERT INTO `Trips` VALUES ('8', '2', '12', '12', 'completed', '2013-10-03');
INSERT INTO `Trips` VALUES ('9', '3', '10', '12', 'completed', '2013-10-03');
INSERT INTO `Trips` VALUES ('10', '4', '13', '12', 'cancelled_by_driver', '2013-10-03');

DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `Users_Id` int(11) DEFAULT NULL,
  `Banned` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Role` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of Users
-- ----------------------------
INSERT INTO `Users` VALUES ('1', 'No', 'client');
INSERT INTO `Users` VALUES ('2', 'Yes', 'client');
INSERT INTO `Users` VALUES ('3', 'No', 'client');
INSERT INTO `Users` VALUES ('4', 'No', 'client');
INSERT INTO `Users` VALUES ('10', 'No', 'driver');
INSERT INTO `Users` VALUES ('11', 'No', 'driver');
INSERT INTO `Users` VALUES ('12', 'No', 'driver');
INSERT INTO `Users` VALUES ('13', 'No', 'driver');

-- mysql have no full join,if q1 is null ,res is not null
select q1.Request_at, round( ifnull(q2.cnt2,0)/(q1.cnt1+ifnull(q2.cnt2,0)),2) rate
	from (
	select t1.Request_at,count( t1.Status) cnt1
	from Trips t1,Users u
	where
	u.Users_Id = t1.Client_Id
	and u.Banned = 'No'
	and t1.Status='completed'
	and t1.Request_at BETWEEN '2013-10-01' AND '2013-10-03'
	group by t1.Request_at
	) q1 left join
	(
	select t2.Request_at,count(t2.Status) cnt2
	from Trips t2,Users u2
	where
	u2.Users_Id = t2.Client_Id
	and u2.Banned = 'No'
	and t2.Status!='completed'
	and t2.Request_at BETWEEN '2013-10-01' AND '2013-10-03'
	group by t2.Request_at
	) q2
	on q1.Request_at = q2.Request_at
group by q1.Request_at having count(q1.Request_at)>0;

-- use sum and if
SELECT Request_at Day, round(sum(if(Status = 'completed', 0, 1))/count(*),2) 'Cancellation Rate'
FROM Trips t
LEFT JOIN Users t1 ON t.Client_Id = t1.Users_Id
WHERE t1.Banned = 'No'
AND Request_at BETWEEN '2013-10-01' AND '2013-10-03' GROUP BY t.Request_at


select b.Request_at as Day,
			 case
			 when cancel is null then round(0,2)
			 else round(cancel/total,2)
			 end as 'Cancellation Rate'
from (
			 select
				 Request_at,
				 count(1) as cancel
			 from Trips left join Users
					 on Trips.Client_Id = Users.Users_Id
			 where Banned = "No" and Status like "cancel%" and datediff(Request_at,"2013-10-01") in (0,1,2)
			 group by Request_at
		 ) a
	right join (
							 select
								 Request_at,
								 count(1) as total
							 from Trips left join Users
									 on Trips.Client_Id = Users.Users_Id
							 where Banned = "No"  and datediff(Request_at,"2013-10-01") in (0,1,2)
							 group by Request_at
						 ) b
		on a.Request_at = b.Request_at
order by Day



select A.Request_at Day, round(B.Status/A.Status,2) "Cancellation Rate"
from
	(select count(Status) Status, Request_at from Trips,Users
	where Client_Id=Users.Users_Id and Users.Banned='No'  group by Request_at
	) A,
	(select B2.Request_at Request_at, ifnull(Status,0)  Status
	 from
		 (select count(Status) Status, Request_at from Trips,Users
		 where Status!='completed'  and Client_Id=Users.Users_Id and Users.Banned='No' group by Request_at) B1
		 right join
		 (select distinct Request_at from Trips) B2
			 on B2.Request_at = B1.Request_at
	) B
where A.Request_at=B.Request_at and date(A.Request_at) between date("2013-10-01") and date("2013-10-03")

