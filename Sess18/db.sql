-- 1. Tạo bảng Product theo cấu trúc mô tả
CREATE TABLE Product (
    Product_Id SERIAL PRIMARY KEY,
    Product_Name VARCHAR(100) NOT NULL UNIQUE,
    Product_Price FLOAT NOT NULL CHECK (Product_Price > 0),
    Product_Title VARCHAR(200) NOT NULL,
    Product_created DATE NOT NULL,
    Product_catalog VARCHAR(100) NOT NULL,
    Product_Status BIT(1) DEFAULT B'1'
);

-- 2. Procedure: Thêm mới một sản phẩm
CREATE OR REPLACE PROCEDURE add_product(
    p_name VARCHAR, p_price FLOAT, p_title VARCHAR, 
    p_created DATE, p_catalog VARCHAR, p_status BIT
)
LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO Product (Product_Name, Product_Price, Product_Title, Product_created, Product_catalog, Product_Status)
    VALUES (p_name, p_price, p_title, p_created, p_catalog, p_status);
END;
$$;

-- 3. Procedure: Cập nhật một sản phẩm theo mã
CREATE OR REPLACE PROCEDURE update_product(
    p_id INT, p_name VARCHAR, p_price FLOAT, p_title VARCHAR, 
    p_created DATE, p_catalog VARCHAR, p_status BIT
)
LANGUAGE plpgsql AS $$
BEGIN
    UPDATE Product 
    SET Product_Name = p_name, Product_Price = p_price, Product_Title = p_title, 
        Product_created = p_created, Product_catalog = p_catalog, Product_Status = p_status
    WHERE Product_Id = p_id;
END;
$$;

-- 4. Procedure: Xóa một sản phẩm theo mã
CREATE OR REPLACE PROCEDURE delete_product(p_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM Product WHERE Product_Id = p_id;
END;
$$;

-- 5. Function: Lấy tất cả thông tin sản phẩm
CREATE OR REPLACE FUNCTION get_all_products()
RETURNS TABLE(id INT, name VARCHAR, price FLOAT, title VARCHAR, created DATE, catalog VARCHAR, status BIT)
LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT Product_Id, Product_Name, Product_Price, Product_Title, Product_created, Product_catalog, Product_Status FROM Product;
END;
$$;

-- 6. Function: Lấy thông tin chi tiết một sản phẩm theo mã
CREATE OR REPLACE FUNCTION get_product_by_id(p_id INT)
RETURNS TABLE(id INT, name VARCHAR, price FLOAT, title VARCHAR, created DATE, catalog VARCHAR, status BIT)
LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT Product_Id, Product_Name, Product_Price, Product_Title, Product_created, Product_catalog, Product_Status FROM Product WHERE Product_Id = p_id;
END;
$$;

-- 7. Function: Tìm kiếm sản phẩm theo tên (tương đối)
CREATE OR REPLACE FUNCTION search_product_by_name(p_name VARCHAR)
RETURNS TABLE(id INT, name VARCHAR, price FLOAT, title VARCHAR, created DATE, catalog VARCHAR, status BIT)
LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT Product_Id, Product_Name, Product_Price, Product_Title, Product_created, Product_catalog, Product_Status 
                 FROM Product WHERE LOWER(Product_Name) LIKE LOWER(CONCAT('%', p_name, '%'));
END;
$$;

-- 8. Function: Thống kê số lượng sản phẩm theo danh mục
CREATE OR REPLACE FUNCTION count_products_by_catalog()
RETURNS TABLE(catalog VARCHAR, total_count BIGINT)
LANGUAGE plpgsql AS $$
BEGIN
    RETURN QUERY SELECT Product_catalog, COUNT(*) FROM Product GROUP BY Product_catalog;
END;
$$;

-- 9. Function phụ bổ trợ: Kiểm tra sự tồn tại của danh mục
CREATE OR REPLACE FUNCTION check_catalog_exists(p_catalog VARCHAR)
RETURNS BOOLEAN
LANGUAGE plpgsql AS $$
DECLARE
    v_exists BOOLEAN;
BEGIN
    SELECT EXISTS(SELECT 1 FROM Product WHERE LOWER(Product_catalog) = LOWER(p_catalog)) INTO v_exists;
    RETURN v_exists;
END;
$$;