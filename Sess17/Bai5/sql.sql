CREATE DATABASE IF NOT EXISTS company_db;
USE company_db;

-- 1. Bảng Employee
CREATE TABLE IF NOT EXISTS employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    salary DECIMAL(10,2) NOT NULL
);

-- 2. Bảng Project
CREATE TABLE IF NOT EXISTS project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    budget DECIMAL(10,2) NOT NULL
);

-- 3. Bảng trung gian Assignment (Mối quan hệ Nhiều - Nhiều)
CREATE TABLE IF NOT EXISTS assignment (
    employee_id INT,
    project_id INT,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (employee_id, project_id),
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
);