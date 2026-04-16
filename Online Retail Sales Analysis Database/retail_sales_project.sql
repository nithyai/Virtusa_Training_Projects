CREATE DATABASE IF NOT EXISTS retail_db;
USE retail_db;

CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(50),
    city VARCHAR(50)
);

CREATE TABLE Products (
    product_id INT PRIMARY KEY,
    name VARCHAR(50),
    category VARCHAR(50),
    price DECIMAL(10,2)
);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    date DATE,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Order_Items (
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

INSERT INTO Customers (customer_id, name, city) VALUES
(1, 'Arjun', 'Chennai'),
(2, 'Meena', 'Bangalore'),
(3, 'Rahul', 'Hyderabad'),
(4, 'Priya', 'Chennai'),
(5, 'Kiran', 'Mumbai');

INSERT INTO Products (product_id, name, category, price) VALUES
(101, 'Laptop', 'Electronics', 55000),
(102, 'Mobile', 'Electronics', 20000),
(103, 'Headphones', 'Electronics', 2000),
(104, 'Shoes', 'Fashion', 3000),
(105, 'Watch', 'Fashion', 2500);

INSERT INTO Orders (order_id, customer_id, date) VALUES
(1, 1, '2024-01-10'),
(2, 2, '2024-02-15'),
(3, 1, '2024-03-05'),
(4, 3, '2024-03-20'),
(5, 4, '2024-04-01');

INSERT INTO Order_Items (order_id, product_id, quantity) VALUES
(1, 101, 1),
(1, 103, 2),
(2, 102, 1),
(3, 104, 1),
(4, 105, 3),
(5, 101, 1);

SELECT * FROM Customers;
SELECT * FROM Products;
SELECT * FROM Orders;
SELECT * FROM Order_Items;

SELECT p.name, SUM(oi.quantity) AS total_sold
FROM Products p
JOIN Order_Items oi ON p.product_id = oi.product_id
GROUP BY p.name
ORDER BY total_sold DESC;

SELECT c.name, SUM(p.price * oi.quantity) AS total_spent
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY c.name
ORDER BY total_spent DESC;

SELECT MONTH(o.date) AS month, SUM(p.price * oi.quantity) AS revenue
FROM Orders o
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY MONTH(o.date)
ORDER BY month;

SELECT p.category, SUM(p.price * oi.quantity) AS total_sales
FROM Products p
JOIN Order_Items oi ON p.product_id = oi.product_id
GROUP BY p.category;

SELECT name
FROM Customers
WHERE customer_id NOT IN (
    SELECT customer_id FROM Orders
);