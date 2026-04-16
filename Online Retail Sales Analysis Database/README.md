# Online Retail Sales Analysis Database

This project is designed to analyze retail sales data using SQL by creating a structured relational database.

## Database Structure

The database consists of the following tables:

- Customers (customer_id, name, city)
- Products (product_id, name, category, price)
- Orders (order_id, customer_id, date)
- Order_Items (order_id, product_id, quantity)

## Features

- Stores customer, product, and order details
- Handles multiple products per order using Order_Items table
- Maintains relationships using primary and foreign keys

## SQL Analysis Performed

- Top-selling products based on quantity sold
- Most valuable customers based on total spending
- Monthly revenue calculation
- Category-wise sales analysis
- Identification of inactive customers

## Tools Used

- MySQL Workbench
- SQL

## How to Run

1. Open MySQL Workbench
2. Run the SQL script file
3. Execute queries to view results
