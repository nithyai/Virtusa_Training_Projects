DROP DATABASE IF EXISTS DigitalLibrary;
CREATE DATABASE DigitalLibrary;
USE DigitalLibrary;

CREATE TABLE Books (
    BookID INT PRIMARY KEY,
    Title VARCHAR(100),
    Author VARCHAR(100),
    Category VARCHAR(50)
);

CREATE TABLE Students (
    StudentID INT PRIMARY KEY,
    StudentName VARCHAR(100),
    LastBorrowDate DATE
);

CREATE TABLE IssuedBooks (
    IssueID INT PRIMARY KEY,
    StudentID INT,
    BookID INT,
    IssueDate DATE,
    ReturnDate DATE,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

INSERT INTO Books VALUES
(1, 'Wings of Fire', 'A P J Abdul Kalam', 'Biography'),
(2, 'Operating Systems', 'Galvin', 'Science'),
(3, 'Harry Potter', 'J K Rowling', 'Fiction');

INSERT INTO Students VALUES
(101, 'Arun', '2023-01-10'),
(102, 'Priya', '2022-12-05');

INSERT INTO IssuedBooks VALUES
(1, 101, 1, DATE_SUB(CURDATE(), INTERVAL 20 DAY), NULL),
(2, 102, 2, DATE_SUB(CURDATE(), INTERVAL 5 DAY), CURDATE()),
(3, 101, 3, DATE_SUB(CURDATE(), INTERVAL 30 DAY), NULL);

SELECT 
    s.StudentName,
    b.Title,
    i.IssueDate
FROM IssuedBooks i
JOIN Students s ON i.StudentID = s.StudentID
JOIN Books b ON i.BookID = b.BookID
WHERE i.ReturnDate IS NULL
AND DATEDIFF(CURDATE(), i.IssueDate) > 14;

SELECT 
    b.Category,
    COUNT(*) AS Total_Borrows
FROM IssuedBooks i
JOIN Books b ON i.BookID = b.BookID
GROUP BY b.Category
ORDER BY Total_Borrows DESC;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM Students
WHERE StudentID NOT IN (
    SELECT StudentID FROM (
        SELECT DISTINCT StudentID FROM IssuedBooks
        WHERE IssueDate >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
    ) AS temp
);