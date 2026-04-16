**Digital Library Project
Project Description**

This project is a simple Digital Library Management System using SQL. It is used to store and manage book details, student details, and issued book records. It also helps to find overdue books and most borrowed categories.

**Tools Used**
MySQL Workbench
SQL

**Tables Created**
Books
Students
IssuedBooks

**Database Creation**
CREATE DATABASE DigitalLibrary;
USE DigitalLibrary;

**Output 1: Database created**
<img width="824" height="524" alt="image" src="https://github.com/user-attachments/assets/5f3a2fab-2f74-4176-ae43-d5241ffa0a19" />

**Table Creation**
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
    ReturnDate DATE
);

**Output 2: Tables created**
<img width="1135" height="867" alt="image" src="https://github.com/user-attachments/assets/7fb8000f-31cc-422a-b300-f719742f4f0d" />

**Data Insertion**
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

**Output 3: - Data inserted**
<img width="1217" height="855" alt="image" src="https://github.com/user-attachments/assets/e96470dc-d3ac-4caa-9bbb-dc3c152d2370" />

**Overdue Books Query**
SELECT 
    s.StudentName,
    b.Title,
    i.IssueDate
FROM IssuedBooks i
JOIN Students s ON i.StudentID = s.StudentID
JOIN Books b ON i.BookID = b.BookID
WHERE i.ReturnDate IS NULL
AND DATEDIFF(CURDATE(), i.IssueDate) > 14;

**Output 4: Overdue books result**
<img width="1007" height="528" alt="image" src="https://github.com/user-attachments/assets/023e2002-3816-4541-b8df-b9f578f60334" />

**Popular Category Query**
SELECT 
    b.Category,
    COUNT(*) AS Total_Borrows
FROM IssuedBooks i
JOIN Books b ON i.BookID = b.BookID
GROUP BY b.Category;

**Output 5: Category result**
<img width="1017" height="552" alt="image" src="https://github.com/user-attachments/assets/77af92d4-6897-4f1c-9ffd-26124ab5b011" />

**Delete Inactive Students**
SET SQL_SAFE_UPDATES = 0;

DELETE FROM Students
WHERE StudentID NOT IN (
    SELECT DISTINCT StudentID FROM IssuedBooks
    WHERE IssueDate >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
);

**Output 6: Delete operation result**
<img width="1160" height="553" alt="image" src="https://github.com/user-attachments/assets/5a70e17b-1327-422a-b397-09781c5fa7cd" />

**Conclusion**

This project helps to manage library records using SQL. It is useful for tracking borrowed books, overdue books, and analyzing book categories.


