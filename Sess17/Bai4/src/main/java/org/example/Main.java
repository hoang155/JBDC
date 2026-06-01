package org.example;
import entity.Customer;
import entity.Order;
import entity.Product;

import java.util.Scanner;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        OrderManagement manager = new OrderManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== SHOP & ORDER MANAGEMENT SYSTEM ==========");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Đăng ký thông tin khách hàng mới (Hỗ trợ tạo đơn)");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Tạo đơn hàng mới");
            System.out.println("5. Hiển thị toàn bộ danh sách đơn hàng");
            System.out.println("6. Tìm kiếm đơn hàng theo mã khách hàng");
            System.out.println("7. Thoát hệ thống");
            System.out.print("Vui lòng lựa chọn chức năng (1-7): ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên sản phẩm: ");
                        String pName = sc.nextLine().trim();
                        if (pName.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Tên sản phẩm không được bỏ trống!");
                            break;
                        }
                        System.out.print("Nhập giá sản phẩm: ");
                        double pPrice = Double.parseDouble(sc.nextLine().trim());
                        if (pPrice <= 0) {
                            System.out.println("⚠️ Lỗi: Giá sản phẩm phải lớn hơn 0!");
                            break;
                        }
                        manager.addProduct(new Product(pName, pPrice));
                        break;

                    case 2:
                        System.out.print("Nhập tên khách hàng mới: ");
                        String cName = sc.nextLine().trim();
                        System.out.print("Nhập email khách hàng: ");
                        String cEmail = sc.nextLine().trim();
                        if (cName.isEmpty() || cEmail.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Không được để trống thông tin!");
                            break;
                        }
                        manager.addCustomer(new Customer(cName, cEmail));
                        break;

                    case 3:
                        System.out.print("Nhập mã ID khách hàng cần cập nhật: ");
                        int uId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập tên khách hàng mới: ");
                        String uName = sc.nextLine().trim();
                        System.out.print("Nhập email khách hàng mới: ");
                        String uEmail = sc.nextLine().trim();

                        if (uName.isEmpty() || uEmail.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Thông tin chỉnh sửa không được bỏ trống!");
                            break;
                        }
                        manager.updateCustomer(uId, new Customer(uName, uEmail));
                        break;

                    case 4:
                        System.out.print("Nhập mã ID khách hàng mua hàng: ");
                        int customerId = Integer.parseInt(sc.nextLine().trim());
                        if (!manager.isCustomerExists(customerId)) {
                            System.out.println("❌ Lỗi: Mã ID khách hàng này không tồn tại trên hệ thống!");
                            break;
                        }

                        System.out.print("Nhập mã ID sản phẩm chọn mua: ");
                        int productId = Integer.parseInt(sc.nextLine().trim());
                        double productPrice = manager.getProductPrice(productId);
                        if (productPrice == -1) {
                            System.out.println("❌ Lỗi: Mã ID sản phẩm này không tồn tại!");
                            break;
                        }

                        System.out.print("Nhập số lượng mua: ");
                        int quantity = Integer.parseInt(sc.nextLine().trim());
                        if (quantity <= 0) {
                            System.out.println("⚠️ Lỗi: Số lượng sản phẩm mua phải lớn hơn 0!");
                            break;
                        }

                        // Tính tổng tiền tự động dựa trên: giá sản phẩm * số lượng
                        double totalAmount = productPrice * quantity;
                        Date currentDate = new Date(System.currentTimeMillis());

                        Order newOrder = new Order(customerId, currentDate, totalAmount);
                        manager.createOrder(newOrder);
                        break;

                    case 5:
                        manager.listAllOrders();
                        break;

                    case 6:
                        System.out.print("Nhập mã ID khách hàng cần tra cứu đơn hàng: ");
                        int searchId = Integer.parseInt(sc.nextLine().trim());
                        manager.getOrdersByCustomer(searchId);
                        break;

                    case 7:
                        System.out.println("Hệ thống đóng. Chúc bạn một ngày tốt lành!");
                        sc.close();
                        return;

                    default:
                        System.out.println("⚠️ Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi nhập sai kiểu: Vui lòng nhập số hợp lệ đối với ID, Giá, Số lượng hay Lựa chọn menu!");
            } catch (Exception e) {
                System.out.println("❌ Đã xảy ra lỗi hệ thống: " + e.getMessage());
            }
        }
    }
}