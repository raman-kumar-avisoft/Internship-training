-- Table: Person

-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | PersonId    | int     |
-- | FirstName   | varchar |
-- | LastName    | varchar |
-- +-------------+---------+
-- PersonId is the primary key column for this table.
-- Table: Address

-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | AddressId   | int     |
-- | PersonId    | int     |
-- | City        | varchar |
-- | State       | varchar |
-- +-------------+---------+
-- AddressId is the primary key column for this table.


-- Write a SQL query for a report that provides the following 
-- information for each person in the Person table, regardless if there is an address for each of those people:

-- FirstName, LastName, City, State

CREATE TABLE person 
(personId INT PRIMARY KEY, firstName VARCHAR(20), lastName VARCHAR(20));

CREATE TABLE address
(addressId INT PRIMARY KEY, personId INT, city VARCHAR(20), state VARCHAR(20));

INSERT INTO person VALUES
(1,'raman', 'malik'),
(2,'rajat', 'dhalu'),
(3,'bruh', 'helicopter'),
(4,'ankit', 'dhandaliya');

INSERT INTO address VALUES
(1,1,'chd','hry'),
(2,2,'chd','hry'),
(3,3,'nagpur','hry'),
(4,5,'kochi','hry');

SELECT
    P.FirstName,
    P.LastName,
    COALESCE(A.City, 'N/A') AS City,
    COALESCE(A.State, 'N/A') AS State
FROM
    Person P
LEFT JOIN
    Address A ON P.PersonId = A.PersonId;

