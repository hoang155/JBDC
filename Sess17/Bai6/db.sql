CREATE DATABASE IF NOT EXISTS school_db;
USE school_db;

-- 1. Bảng Student
CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- 2. Bảng Course
CREATE TABLE IF NOT EXISTS course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    credits INT NOT NULL
);

-- 3. Bảng trung gian Enrollment
CREATE TABLE IF NOT EXISTS enrollment (
    student_id INT,
    course_id INT,
    grade DECIMAL(5,2) DEFAULT NULL, -- Có thể NULL nếu chưa có điểm
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);