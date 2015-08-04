--Consecutive Numbers
--https://leetcode.com/problems/consecutive-numbers/

-- ----------------------------
-- Table structure for Logs
-- ----------------------------
DROP TABLE IF EXISTS `Logs`;
CREATE TABLE `Logs` (
  `Id` int(11) DEFAULT NULL,
  `Num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of Logs
-- ----------------------------
INSERT INTO `Logs` VALUES ('1', '1');
INSERT INTO `Logs` VALUES ('2', '1');
INSERT INTO `Logs` VALUES ('3', '1');
INSERT INTO `Logs` VALUES ('4', '2');
INSERT INTO `Logs` VALUES ('5', '1');
INSERT INTO `Logs` VALUES ('6', '2');
INSERT INTO `Logs` VALUES ('7', '2');


--not pass of -8
{"headers": {"Logs": ["Id", "Num"]}, "rows": {"Logs": [
[0, -9],
[1, -9],
[2, -9],
[3, 8],
[4, -7],
[5, 5],
[6, -5],
[7, 2],
[8, 9],
[9, -8],
[10, -2],
[11, 0],
[12, -8],
[13, -8],
[14, -9],
[15, -3],
[16, 5],
[17, -6],
[18, 7],
[19, 5],
[20, 10],
[21, -4],
[22, -9],
[23, -3],
[24, 0],
[25, 9],
[26, 5],
[27, -2],
[28, -4],
[29, -5],
[30, -6],
[31, -5], [32, -10], [33, 1], [34, -7], [35, -7], [36, 4], [37, -2], [38, 6], [39, 1], [40, -9], [41, 6], [42, -2], [43, 1], [44, 5], [45, 10], [46, 2],
[47, -8],
[48, -8],
[49, -1], [50, 5], [51, 0], [52, 2], [53, -5], [54, -2], [55, 2], [56, -5], [57, 1], [58, 9], [59, -4], [60, 2], [61, 10], [62, 4], [63, -9],
[64, -8], [65, 2], [66, 3], [67, 5], [68, 3], [69, 7], [70, 8], [71, -5], [72, 2], [73, 2], [74, 2], [75, -4], [76, -4], [77, -7], [78, 10], [79, 0], [80, 2], [81, 10], [82, 0], [83, 6], [84, 2], [85, 5], [86, 10], [87, -3], [88, 10], [89, 4], [90, -5], [91, -10], [92, 1], [93, 0], [94, -1], [95, 4], [96, -7], [97, -3], [98, 5], [99, 3]]}}



--solution
select l1.Num
from Logs l1
left join Logs l2 on l1.Num=l2.Num
left join Logs l3 on l3.Num=l2.Num
where l1.Id-l2.Id =1 and l2.Id-l3.Id =1
group by l1.Num
