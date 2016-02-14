--Department Top Three Salaries
--https://com.jason.leetcode.com/problems/department-top-three-salaries/

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
INSERT INTO `Employee` VALUES ('1', 'Joe', '70', '1');
INSERT INTO `Employee` VALUES ('2', 'Henry', '80', '2');
INSERT INTO `Employee` VALUES ('3', 'Sam', '60', '2');
INSERT INTO `Employee` VALUES ('4', 'Max', '90', '1');
INSERT INTO `Employee` VALUES ('5', 'Janet', '69', '1');
INSERT INTO `Employee` VALUES ('6', 'Randy', '85', '1');


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


--s1,bigger salary got <3 bigger salary than it
SELECT
    d.Name Department, e.Name Employee, e.Salary Salary
FROM
    (SELECT DepartmentId, Name, Salary
        FROM Employee e3
        WHERE 3 > (
            SELECT COUNT(e2.Salary)
            FROM (
                SELECT DISTINCT Salary, DepartmentId
                FROM Employee
            ) e2
            WHERE
                e3.DepartmentId = e2.DepartmentId
            AND
                e3.Salary < e2.Salary
        )
) e
LEFT JOIN
    Department d
ON
    e.DepartmentId = d.Id
WHERE
    d.Name IS NOT NULL


--s2 count+having
SELECT q0.Department AS Department, q0.Name AS Employee, q0.Salary AS Salary
  FROM
  (SELECT t0.Id AS Id, t0.Name AS Name, t0.Salary AS Salary, t0.DepartmentId AS DepartmentId, t1.Name AS Department
     FROM
       Employee AS t0
     JOIN
       Department AS t1
     ON t0.DepartmentId = t1.Id
  ) AS q0
  JOIN
  (SELECT q1.Salary AS Salary, q1.DepartmentId AS DepartmentId
    FROM
      (SELECT DISTINCT t2.Salary AS Salary, t2.DepartmentId AS DepartmentId
        FROM
         Employee AS t2) AS q1
    LEFT JOIN
      (SELECT DISTINCT t3.Salary AS Salary, t3.DepartmentId AS DepartmentId
        FROM
         Employee AS t3) AS q2
    ON
    q1.Salary < q2.Salary AND q1.DepartmentId = q2.DepartmentId
    GROUP BY Salary, DepartmentId
    HAVING COUNT(q2.Salary) <= 2) AS q3
  ON
    q0.Salary = q3.Salary AND q0.DepartmentId = q3.DepartmentId
  ORDER BY Department, Salary DESC