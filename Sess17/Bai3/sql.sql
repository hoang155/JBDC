-- 1. Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

-- 2. Tạo bảng books theo cấu trúc yêu cầu
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    published_year INT NOT NULL, -- MySQL không có kiểu dữ liệu YEAR riêng biệt hoạt động tốt trong JDBC, dùng INT để đồng bộ
    price DECIMAL(10, 2) NOT NULL
);