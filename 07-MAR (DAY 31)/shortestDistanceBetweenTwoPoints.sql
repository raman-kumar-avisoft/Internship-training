-- Table point holds the x coordinate of some points on x-axis in a plane, which are all integers.

-- Write a query to find the shortest distance between two points in these points.


-- | x   |
-- |-----|
-- | -1  |
-- | 0   |
-- | 2   |

-- The shortest distance is '1' obviously, which is from point '-1' to '0'. So the output is as below:


-- | shortest|
-- |---------|
-- |  1      |

-- Create a DB
CREATE DATABASE IF NOT EXISTS javaInternshipProgram;

-- Use a DB
USE javaInternshipProgram;

-- Create the point table
CREATE TABLE point (
    x INT
);

-- Insert sample data into the point table
INSERT INTO point (x) VALUES
(-1),
(0),
(2);

-- Query to find the shortest distance between two points
SELECT MIN(ABS(p1.x - p2.x)) AS shortest
FROM point p1
JOIN point p2 ON p1.x <> p2.x;