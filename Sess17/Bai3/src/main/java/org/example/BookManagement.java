package org.example;
import java.sql.*;

public class BookManager {
    private final String url = "jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC";
    private final String user = "root";       // Thay bằng tài khoản MySQL của bạn
    private final String password = "password"; // Thay bằng mật khẩu MySQL của bạn

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Kiểm tra trùng lặp (Tên sách + Tác giả)
    private boolean isBookExists(String title, String author) throws SQLException {
        String sql = "SELECT COUNT(*) FROM books WHERE LOWER(title) = LOWER(?) AND LOWER(author) = LOWER(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Kiểm tra sách tồn tại theo ID
    private boolean isIdExists(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM books WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // 1. Thêm sách mới (Kiểm tra trùng trước khi thêm)
    public void addBook(Book book) {
        try {
            if (isBookExists(book.getTitle(), book.getAuthor())) {
                System.out.println("❌ Lỗi: Sách này (Tên sách + Tác giả) đã tồn tại trong thư viện!");
                return;
            }

            String sql = "INSERT INTO books (title, author, published_year, price) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setInt(3, book.getPublishedYear());
                pstmt.setDouble(4, book.getPrice());
                pstmt.executeUpdate();
                System.out.println("🎉 Thêm sách mới thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // 2. Cập nhật thông tin sách (Chỉ cập nhật nếu sách tồn tại)
    public void updateBook(int id, Book book) {
        try {
            if (!isIdExists(id)) {
                System.out.println("❌ Lỗi: Không tìm thấy sách nào có ID = " + id);
                return;
            }

            String sql = "UPDATE books SET title = ?, author = ?, published_year = ?, price = ? WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getAuthor());
                pstmt.setInt(3, book.getPublishedYear());
                pstmt.setDouble(4, book.getPrice());
                pstmt.setInt(5, id);
                pstmt.executeUpdate();
                System.out.println("🎉 Cập nhật thông tin sách thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // 3. Xóa sách theo ID (Có kiểm tra tồn tại)
    public void deleteBook(int id) {
        try {
            if (!isIdExists(id)) {
                System.out.println("❌ Lỗi: Không tìm thấy sách nào có ID = " + id);
                return;
            }

            String sql = "DELETE FROM books WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("🎉 Xóa sách thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // 4. Tìm kiếm sách theo tác giả
    public void findBooksByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE LOWER(author) LIKE LOWER(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + author + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n--- KẾT QUẢ TÌM KIẾM THEO TÁC GIẢ ---");
                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    System.out.printf("ID: %-3d | Tên: %-25s | Tác giả: %-20s | Năm: %-5d | Giá: %.2f\n",
                            rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                            rs.getInt("published_year"), rs.getDouble("price"));
                }
                if (!hasData) {
                    System.out.println("Không tìm thấy cuốn sách nào của tác giả: " + author);
                }
                System.out.println("----------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // 5. Hiển thị tất cả sách
    public void listAllBooks() {
        String sql = "SELECT * FROM books";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n==================== DANH SÁCH SÁCH TRONG THƯ VIỆN ====================");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("ID: %-3d | Tên: %-25s | Tác giả: %-20s | Năm: %-5d | Giá: %.2f VNĐ\n",
                        rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getInt("published_year"), rs.getDouble("price"));
            }
            if (!hasData) {
                System.out.println("Hiện tại thư viện trống.");
            }
            System.out.println("=======================================================================");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi hiển thị danh sách sách: " + e.getMessage());
        }
    }
}