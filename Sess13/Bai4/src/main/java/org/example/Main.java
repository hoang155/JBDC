package org.example;
import entity.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OrderManager manager = new OrderManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n******************** MENU QUẢN LÝ ĐƠN HÀNG ********************");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Sửa đơn hàng");
            System.out.println("3. Xóa đơn hàng");
            System.out.println("4. Hiển thị danh sách đơn hàng");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    String code;
                    // Vòng lặp validate bắt buộc nhập mã đơn hàng, không cho phép để trống
                    while (true) {
                        System.out.println("Nhập mã đơn hàng:");
                        code = scanner.nextLine().trim();
                        if (code.isEmpty()) {
                            System.out.println("\nVui lòng ko để trống !");
                            continue;
                        }

                        // Kiểm tra trùng lặp mã đơn hàng nếu cần
                        if (manager.findIndexByCode(code) != -1) {
                            System.out.println("❌ Lỗi: Mã đơn hàng này đã tồn tại trên hệ thống!");
                            continue;
                        }
                        break;
                    }

                    System.out.println("Nhập tên khách hàng:");
                    String name = scanner.nextLine().trim();

                    Order newOrder = new Order(code, name);
                    manager.add(newOrder);
                    break;

                case "2":
                    if (manager.isEmpty()) {
                        System.out.println("⚠️ Danh sách trống, không có đơn hàng nào để sửa.");
                        break;
                    }
                    // Hiển thị danh sách đơn hàng hiện có trước khi sửa
                    manager.display();

                    System.out.println("Nhập mã đơn hàng cần sửa:");
                    String updateCode = scanner.nextLine().trim();

                    int updateIndex = manager.findIndexByCode(updateCode);
                    if (updateIndex == -1) {
                        System.out.println("❌ Không tìm thấy đơn hàng nào có mã = " + updateCode);
                    } else {
                        System.out.println("Nhập tên khách hàng mới:");
                        String newName = scanner.nextLine().trim();

                        // Giữ lại mã đơn hàng cũ và cập nhật tên khách hàng mới
                        Order updatedOrder = new Order(updateCode, newName);
                        manager.update(updateIndex, updatedOrder);
                    }
                    break;

                case "3":
                    if (manager.isEmpty()) {
                        System.out.println("⚠️ Danh sách trống, không có đơn hàng nào để xóa.");
                        break;
                    }
                    // Hiển thị danh sách đơn hàng hiện có trước khi xóa
                    manager.display();

                    System.out.println("Nhập mã đơn hàng cần xóa:");
                    String deleteCode = scanner.nextLine().trim();

                    int deleteIndex = manager.findIndexByCode(deleteCode);
                    if (deleteIndex == -1) {
                        System.out.println("Không tìm thấy đơn hàng nào có mã = " + deleteCode);
                    } else {
                        manager.delete(deleteIndex);
                    }
                    break;

                case "4":
                    manager.display();
                    break;

                case "5":
                    System.out.println("👋 Đang tắt chương trình quản lý đơn hàng. Tạm biệt!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ! Vui lòng nhập từ 1 đến 5.");
            }
        }
    }
}