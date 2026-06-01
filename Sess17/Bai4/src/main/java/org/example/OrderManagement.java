package org.example;
import entity.Customer;
import entity.Order;
import entity.Product;

import java.sql.*;

public class OrderManagement {
    private final String url = "jdbc:mysql://localhost:3306/shop_db?useSSL=false&serverTimezone=UTC";
    private final String user = "root";       // Thay bằng username của bạn
    private final String password = "password"; // Thay bằng password của bạn

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Chức năng 1: Thêm sản phẩm mới (Kiểm tra trùng tên)
    public void addProduct(Product product) {
        String checkSql = "SELECT COUNT(*) FROM products WHERE LOWER(name) = LOWER(?)";
        String insertSql = "INSERT INTO products (name, price) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            checkStmt.setString(1, product.getName());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("❌ Lỗi: Tên sản phẩm '" + product.getName() + "' đã tồn tại!");
                    return;
                }
            }

            insertStmt.setString(1, product.getName());
            insertStmt.setDouble(2, product.getPrice());
            insertStmt.executeUpdate();
            System.out.println("🎉 Thêm sản phẩm mới thành công!");

        } catch (SQLException e) {
            System.out.println("❌ Lỗi DB sản phẩm: " + e.getMessage());
        }
    }

    // Chức năng 2: Cập nhật thông tin khách hàng (Báo lỗi nếu không tồn tại)
    public void updateCustomer(int customerId, Customer customer) {
        String checkSql = "SELECT COUNT(*) FROM customers WHERE id = ?";
        String updateSql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            checkStmt.setInt(1, customerId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    System.out.println("❌ Lỗi: Không tìm thấy khách hàng có ID = " + customerId);
                    return;
                }
            }

            updateStmt.setString(1, customer.getName());
            updateStmt.setString(2, customer.getEmail());
            updateStmt.setInt(3, customerId);
            updateStmt.executeUpdate();
            System.out.println("🎉 Cập nhật thông tin khách hàng thành công!");

        } catch (SQLException e) {
            System.out.println("❌ Lỗi DB cập nhật khách hàng (Có thể do trùng Email): " + e.getMessage());
        }
    }

    // Tiện ích bổ sung cho Chức năng 3: Lấy giá sản phẩm theo ID và kiểm tra sự tồn tại của khách hàng
    public double getProductPrice(int productId) throws SQLException {
        String sql = "SELECT price FROM products WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getDouble("price");
            }
        }
        return -1;
    }

    public boolean isCustomerExists(int customerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Chức năng 3: Tạo đơn hàng mới
    public void createOrder(Order order) {
        String sql = "INSERT INTO orders (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDate(2, order.getOrderDate());
            pstmt.setDouble(3, order.getTotalAmount());
            pstmt.executeUpdate();
            System.out.println("🎉 Tạo đơn hàng thành công! Tổng tiền: " + order.getTotalAmount() + " VNĐ");

        } catch (SQLException e) {
            System.out.println("❌ Lỗi tạo đơn hàng: " + e.getMessage());
        }
    }

    // Chức năng 4: Hiển thị tất cả đơn hàng kèm tên KH, ngày đặt, tổng tiền
    public void listAllOrders() {
        String sql = "SELECT o.id, c.name AS customer_name, o.order_date, o.total_amount " +
                "FROM orders o JOIN customers c ON o.customer_id = c.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n==================== DANH SÁCH ĐƠN HÀNG ====================");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("Mã ĐH: %-4d | Khách hàng: %-20s | Ngày đặt: %-12s | Tổng tiền: %.2f VNĐ\n",
                        rs.getInt("id"), rs.getString("customer_name"),
                        rs.getDate("order_date").toString(), rs.getDouble("total_amount"));
            }
            if (!hasData) System.out.println("Chưa có đơn hàng nào trong hệ thống.");
            System.out.println("=============================================================");

        } catch (SQLException e) {
            System.out.println("❌ Lỗi hiển thị đơn hàng: " + e.getMessage());
        }
    }

    // Chức năng 5: Tìm kiếm danh sách đơn hàng theo khách hàng cụ thể
    public void getOrdersByCustomer(int customerId) {
        String sql = "SELECT o.id, o.order_date, o.total_amount " +
                "FROM orders o WHERE o.customer_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n--- ĐƠN HÀNG CỦA KHÁCH HÀNG (ID: " + customerId + ") ---");
                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    System.out.printf("Mã ĐH: %-4d | Ngày đặt: %-12s | Tổng tiền: %.2f VNĐ\n",
                            rs.getInt("id"), rs.getDate("order_date").toString(), rs.getDouble("total_amount"));
                }
                if (!hasData) System.out.println("Không tìm thấy đơn hàng nào cho khách hàng này.");
                System.out.println("-----------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi tìm kiếm đơn hàng: " + e.getMessage());
        }
    }

    // Phương thức phụ trợ: Thêm nhanh khách hàng để hỗ trợ test ứng dụng liên kết bảng
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.executeUpdate();
            System.out.println("🎉 Đăng ký thông tin khách hàng thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Không thể thêm khách hàng (Có thể trùng Email): " + e.getMessage());
        }
    }
}