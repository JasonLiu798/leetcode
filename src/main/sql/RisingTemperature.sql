--Rising Temperature
--https://com.jason.leetcode.com/problems/rising-temperature/

DROP TABLE IF EXISTS `Weather`;
CREATE TABLE `Weather` (
  `Id` int(11) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `Temperature` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of Weather
-- ----------------------------
INSERT INTO `Weather` VALUES ('1', '2015-01-01 00:00:00', '10');
INSERT INTO `Weather` VALUES ('2', '2015-01-02 00:00:00', '25');
INSERT INTO `Weather` VALUES ('3', '2015-01-03 00:00:00', '20');
INSERT INTO `Weather` VALUES ('4', '2015-01-04 00:00:00', '30');

select w1.Id from Weather w1,Weather w2
where w1.Temperature> w2.Temperature 
and w2.`Date`  = 
 date_sub( w1.`Date` ,interval 1 day)  