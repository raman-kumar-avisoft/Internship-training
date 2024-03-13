-- 2.Problem statement
-- Select all employee's name and bonus whose bonus is < 1000.

-- Table:Employee

-- +-------+--------+-----------+--------+
-- | empId |  name  | supervisor| salary |
-- +-------+--------+-----------+--------+
-- |   1   | John   |  3        | 1000   |
-- |   2   | Dan    |  3        | 2000   |
-- |   3   | Brad   |  null     | 4000   |
-- |   4   | Thomas |  3        | 4000   |
-- +-------+--------+-----------+--------+
-- empId is the primary key column for this table.
-- Table: Bonus

-- +-------+-------+
-- | empId | bonus |
-- +-------+-------+
-- | 2     | 500   |
-- | 4     | 2000  |
-- +-------+-------+
-- empId is the primary key column for this table.

-- Example output:

--  +-------+-------+
--  | name  | bonus |
-- +-------+-------+
-- | John  | null  |
-- | Dan   | 500   |
-- | Brad  | null  |
-- +-------+-------+

CREATE TABLE employee2 (empId INT PRIMARY KEY, name VARCHAR(20), supervisor VARCHAR(20), salary INT);
CREATE TABLE bonus (empId INT PRIMARY KEY, bonus INT);

INSERT INTO employee2 VALUES
(1, 'John', 3, 1000),
(2, 'Dan', 3, 2000),
(3, 'Brad', null, 4000),
(4, 'Thomas', 3, 4000);

INSERT INTO bonus VALUES
(2,500),
(3,2000);

SELECT name, bonus FROM employee2
LEFT JOIN bonus ON employee2.empid = bonus.empid
WHERE bonus.bonus < 1000 OR bonus.bonus IS NULL;