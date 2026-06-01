-- 1. Tạo bảng lưu trữ thông tin sách
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    price DECIMAL(10, 2)
);

-- 2. Procedure: Thêm sách mới (add_book)
CREATE OR REPLACE PROCEDURE add_book(p_title TEXT, p_author TEXT, p_price DECIMAL)
LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO books (title, author, price) 
    VALUES (p_title, p_author, p_price);
END;
$$;

-- 3. Procedure: Cập nhật thông tin sách (update_book)
CREATE OR REPLACE PROCEDURE update_book(p_id INT, p_title TEXT, p_author TEXT, p_price DECIMAL)
LANGUAGE plpgsql AS $$
BEGIN
    UPDATE books 
    SET title = p_title, author = p_author, price = p_price 
    WHERE id = p_id;
END;
$$;

-- 4. Procedure: Xóa sách (delete_book)
CREATE OR REPLACE PROCEDURE delete_book(p_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM books WHERE id = p_id;
END;
$$;

-- 5. Function: Lấy danh sách sách (list_books)
CREATE OR REPLACE FUNCTION list_books() 
RETURNS TABLE(id INT, title VARCHAR, author VARCHAR, price DECIMAL) 
LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT b.id, b.title, b.author, b.price FROM books b;
END;
$$;