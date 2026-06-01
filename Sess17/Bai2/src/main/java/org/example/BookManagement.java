package org.example;
import java.sql.*;

public class BookManagement {
    // Thay đổi thông tin kết nối phù hợp với PostgreSQL của bạn
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "your_password"; // Thay bằng mật khẩu của bạn

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Chức năng 1: Thêm sách mới
    public void addBook(String title, String author, double price) {
        String sql = "{call add_book(?, ?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setDouble(3, price);
            stmt.execute();
            System.out.println("Thêm sách mới thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu khi thêm sách: " + e.getMessage());
        }
    }

    // Chức năng 2: Hiển thị danh sách sách
    public void listBooks() {
        String sql = "SELECT * FROM list_books()";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n================= DANH SÁCH SÁCH =================");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("ID: %-3d | Tên sách: %-25s | Tác giả: %-20s | Giá: %.2f\n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"));
            }
            if (!hasData) {
                System.out.println("Hiện tại thư viện trống, chưa có cuốn sách nào.");
            }
            System.out.println("==================================================");
        } catch (SQLException e) {
            System.out.println("Lỗi hiển thị danh sách sách: " + e.getMessage());
        }
    }

    // Chức năng 3: Cập nhật thông tin sách
    public void updateBook(int id, String title, String author, double price) {
        String sql = "{call update_book(?, ?, ?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setDouble(4, price);
            stmt.execute();
            System.out.println("Cập nhật thông tin sách thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu khi cập nhật: " + e.getMessage());
        }
    }

    // Chức năng 4: Xóa sách
    public void deleteBook(int id) {
        String sql = "{call delete_book(?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Xóa sách thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi cơ sở dữ liệu khi xóa: " + e.getMessage());
        }
    }
}