
-- Write a SQL query to get the second highest salary from the 
-- Employee table.

-- +----+--------+
-- | Id | Salary |
-- +----+--------+
-- | 1  | 100    |
-- | 2  | 200    |
-- | 3  | 300    |
-- +----+--------+
-- For example, given the above Employee table, the query should return 200 as the second highest salary. If there is no second highest salary, then the query should return null.

-- +---------------------+
-- | SecondHighestSalary |
-- +---------------------+
-- | 200                 |
-- +---------------------+

-- Create a DB
CREATE DATABASE IF NOT EXISTS javaInternshipProgram;

-- Use a DB
USE javaInternshipProgram;

-- Create Employee table
CREATE TABLE Employee (
    Id INT PRIMARY KEY,
    Salary INT CHECK (Salary < 10000) # this is the right syntax but only issue is that check constraint is not fully compatible by mysql 8.0
);

SHOW TABLES;
DROP TABLE Employee;

SELECT * FROM Employee;

-- Insert sample data into Employee table
INSERT INTO Employee (Id, Salary) VALUES
(1, 100),
(2, 200),
(3, 300);

SELECT * FROM Employee;

-- select secondHighestSalary (ONE WAY)
-- SELECT MAX(Salary) AS SecondHighestSalary
-- FROM Employee
-- WHERE Salary < (SELECT MAX(Salary) FROM Employee);

-- select secondHighestSalary (SECOND WAY)
SELECT Salary AS SecondHighestSalary FROM Employee e1
WHERE 2 = (
SELECT COUNT(e2.Salary)
FROM employee e2
WHERE e2.Salary >= e1.Salary
);
 
