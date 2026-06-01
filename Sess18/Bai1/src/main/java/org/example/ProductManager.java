package org.example;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private final String url = "jdbc:postgresql://localhost:5432/postgres"; // Thay bằng tên CSDL của bạn
    private final String user = "postgres";
    private final String password = "your_password"; // Điền password PostgreSQL của bạn

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Chức năng 1: Hiển thị danh sách sản phẩm
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM get_all_products()";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"), rs.getString("name"), rs.getFloat("price"),
                        rs.getString("title"), rs.getDate("created"),
                        rs.getString("catalog"), rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi tải sản phẩm: " + e.getMessage());
        }
        return list;
    }

    // Chức năng 2: Thêm mới sản phẩm sử dụng Transaction
    public void addProduct(Product p) {
        String sql = "{call add_product(?, ?, ?, ?, ?, ?)}";
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); // BẮT ĐẦU TRANSACTION

            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setString(1, p.getName());
                stmt.setDouble(2, p.getPrice());
                stmt.setString(3, p.getTitle());
                stmt.setDate(4, p.getCreated());
                stmt.setString(5, p.getCatalog());

                // Gán giá trị BIT trong PostgreSQL
                stmt.setObject(6, p.getStatus(), java.sql.Types.BIT);
                stmt.execute();
            }

            conn.commit(); // COMMIT TRANSACTION NẾU THÀNH CÔNG
            System.out.println("🎉 Thêm mới sản phẩm vào hệ thống thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Có lỗi xảy ra. Đang Rollback dữ liệu sản phẩm...");
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            System.out.println("❌ Chi tiết lỗi Transaction: " + e.getMessage());
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    // Chức năng 3: Cập nhật thông tin sản phẩm có Transaction
    public void updateProduct(int id, Product p) {
        String sql = "{call update_product(?, ?, ?, ?, ?, ?, ?)}";
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); // BẮT ĐẦU TRANSACTION

            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, p.getName());
                stmt.setDouble(3, p.getPrice());
                stmt.setString(4, p.getTitle());
                stmt.setDate(5, p.getCreated());
                stmt.setString(6, p.getCatalog());
                stmt.setObject(7, p.getStatus(), java.sql.Types.BIT);
                stmt.execute();
            }

            conn.commit();
            System.out.println("🎉 Cập nhật thông tin sản phẩm thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Cập nhật thất bại. Đang thực hiện khôi phục (Rollback)...");
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            System.out.println("❌ Chi tiết lỗi: " + e.getMessage());
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    // Chức năng 4: Xóa sản phẩm theo mã
    public void deleteProduct(int id) {
        String sql = "{call delete_product(?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("🎉 Đã xóa hoàn toàn sản phẩm có mã ID: " + id);
        } catch (SQLException e) {
            System.out.println("❌ Lỗi xóa sản phẩm: " + e.getMessage());
        }
    }

    // Chức năng 5: Tìm kiếm sản phẩm theo tên tương đối
    public List<Product> searchProductByName(String name) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM search_product_by_name(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(
                            rs.getInt("id"), rs.getString("name"), rs.getFloat("price"),
                            rs.getString("title"), rs.getDate("created"),
                            rs.getString("catalog"), rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi tìm kiếm sản phẩm: " + e.getMessage());
        }
        return list;
    }

    // Chức năng 7: Thống kê số lượng sản phẩm theo danh mục
    public void showStatistics() {
        String sql = "SELECT * FROM count_products_by_catalog()";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- BẢNG THỐNG KÊ SẢN PHẨM THEO DANH MỤC ---");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("Danh mục: %-20s | Số lượng sản phẩm: %d\n",
                        rs.getString("catalog"), rs.getLong("total_count"));
            }
            if (!hasData) System.out.println("Hệ thống chưa có dữ liệu sản phẩm để thống kê.");
            System.out.println("------------------------------------------");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi thực hiện thống kê: " + e.getMessage());
        }
    }

    // Tiện ích hỗ trợ: Kiểm tra ID tồn tại
    public boolean isProductIdExists(int id) {
        String sql = "SELECT * FROM get_product_by_id(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }
}