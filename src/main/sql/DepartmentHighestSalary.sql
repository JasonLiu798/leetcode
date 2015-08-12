--Department Highest Salary
--https://com.jason.leetcode.com/problems/department-highest-salary

-- ----------------------------
-- Table structure for Department
-- ----------------------------
DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department` (
  `Id` int(11) DEFAULT NULL,
  `Name` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of Department
-- ----------------------------
INSERT INTO `Department` VALUES ('1', 'IT');
INSERT INTO `Department` VALUES ('2', 'Sales');

-- ----------------------------
-- Table structure for Employee
-- ----------------------------
DROP TABLE IF EXISTS `Employee`;
CREATE TABLE `Employee` (
  `Id` int(11) DEFAULT NULL,
  `Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Salary` int(11) DEFAULT NULL,
  `DepartmentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of Employee
-- ----------------------------
INSERT INTO `Employee` VALUES ('1', 'Joe', '7', '1');
INSERT INTO `Employee` VALUES ('2', 'Henry', '8', '2');
INSERT INTO `Employee` VALUES ('3', 'Sam', '6', '2');
INSERT INTO `Employee` VALUES ('4', 'Max', '9', '1');



select d.Name Department,e.Name Employee,e.Salary
from Employee e
join Department d on  d.Id = e.DepartmentId
where (e.DepartmentId,e.Salary) in
(
select DepartmentId,max(e.Salary)
from Employee e
group by e.DepartmentId
);

