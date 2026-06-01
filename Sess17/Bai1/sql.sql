-- 1. Tạo bảng lưu trữ phim
CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255),
    release_year INT
);

-- 2. Thủ tục thêm phim (add_movie)
CREATE OR REPLACE PROCEDURE add_movie(p_title TEXT, p_director TEXT, p_year INT)
LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO movies (title, director, release_year) 
    VALUES (p_title, p_director, p_year);
END;
$$;

-- 3. Thủ tục cập nhật phim (update_movie)
CREATE OR REPLACE PROCEDURE update_movie(p_id INT, p_title TEXT, p_director TEXT, p_year INT)
LANGUAGE plpgsql AS $$
BEGIN
    UPDATE movies 
    SET title = p_title, director = p_director, release_year = p_year 
    WHERE id = p_id;
END;
$$;

-- 4. Thủ tục xóa phim (delete_movie)
CREATE OR REPLACE PROCEDURE delete_movie(p_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM movies WHERE id = p_id;
END;
$$;

-- 5. Hàm lấy danh sách phim (Trong Postgres, dùng FUNCTION trả về TABLE để lấy danh sách)
CREATE OR REPLACE FUNCTION list_movies() 
RETURNS TABLE(id INT, title VARCHAR, director VARCHAR, release_year INT) 
LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT m.id, m.title, m.director, m.release_year FROM movies m;
END;
$$;