package org.example;
import entity.Product;

import java.util.Scanner;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n******************** PRODUCT MANAGEMENT ********************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("8. Thoát");
            System.out.print("Vui lòng nhập lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        printList(manager.getAllProducts());
                        break;

                    case 2:
                        System.out.println("\n--- THÊM MỚI SẢN PHẨM ---");
                        Product newProduct = inputAndValidateProduct(sc, null);
                        if (newProduct != null) {
                            manager.addProduct(newProduct);
                        }
                        break;

                    case 3:
                        System.out.println("\n--- CẬP NHẬT SẢN PHẨM ---");
                        System.out.print("Nhập mã ID sản phẩm cần cập nhật: ");
                        int uId = Integer.parseInt(sc.nextLine().trim());
                        if (!manager.isProductIdExists(uId)) {
                            System.out.println("❌ Lỗi: Mã sản phẩm ID không tồn tại!");
                            break;
                        }
                        Product updatedProduct = inputAndValidateProduct(sc, uId);
                        if (updatedProduct != null) {
                            manager.updateProduct(uId, updatedProduct);
                        }
                        break;

                    case 4:
                        System.out.print("Nhập mã ID sản phẩm muốn xóa: ");
                        int dId = Integer.parseInt(sc.nextLine().trim());
                        if (!manager.isProductIdExists(dId)) {
                            System.out.println("❌ Lỗi: Không tồn tại sản phẩm mang ID này!");
                        } else {
                            manager.deleteProduct(dId);
                        }
                        break;

                    case 5:
                        System.out.print("Nhập tên sản phẩm cần tìm kiếm (tương đối): ");
                        String searchName = sc.nextLine().trim();
                        if (searchName.isEmpty()) {
                            System.out.println("⚠️ Từ khóa tìm kiếm không được để trống!");
                        } else {
                            printList(manager.searchProductByName(searchName));
                        }
                        break;

                    case 6:
                        List<Product> sortedList = manager.getAllProducts();
                        // Sắp xếp tăng dần theo giá bằng Stream API trong Java
                        sortedList.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                        System.out.println("\n--- DANH SÁCH SẢN PHẨM ĐÃ SẮP XẾP THEO GIÁ TĂNG DẦN ---");
                        printList(sortedList);
                        break;

                    case 7:
                        manager.showStatistics();
                        break;

                    case 8:
                        System.out.println("Đang đóng hệ thống quản lý sản phẩm... Tạm biệt!");
                        sc.close();
                        return;

                    default:
                        System.out.println("⚠️ Lựa chọn mục lục không tồn tại! Vui lòng nhập số từ 1 đến 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi nhập liệu: Lựa chọn menu hoặc mã số ID phải đúng định dạng số nguyên!");
            } catch (Exception e) {
                System.out.println("❌ Có lỗi hệ thống: " + e.getMessage());
            }
        }
    }

    // Hàm tiện ích: In bảng sản phẩm đẹp mắt ra màn hình Console
    private static void printList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Hiện tại danh sách trống không có sản phẩm nào.");
            return;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-20s | %-12s | %-20s | %-12s | %-15s | %-10s\n",
                "ID", "Tên sản phẩm", "Giá (VNĐ)", "Tiêu đề", "Ngày tạo", "Danh mục", "Trạng thái");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        for (Product p : products) {
            String statusText = p.getStatus().equals("1") ? "Kinh doanh" : "Ngừng bán";
            System.out.printf("%-5d | %-20s | %-12.2f | %-20s | %-12s | %-15s | %-10s\n",
                    p.getId(), p.getName(), p.getPrice(), p.getTitle(), p.getCreated().toString(), p.getCatalog(), statusText);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------");
    }

    // Hàm tiện ích: Thu thập dữ liệu và Validate toàn bộ các ràng buộc nghiệp vụ trong ảnh
    private static Product inputAndValidateProduct(Scanner sc, Integer id) {
        try {
            System.out.print("Nhập tên sản phẩm (Unique): ");
            String name = sc.nextLine().trim();
            System.out.print("Nhập giá sản phẩm (>0): ");
            double price = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Nhập tiêu đề sản phẩm: ");
            String title = sc.nextLine().trim();
            System.out.print("Nhập danh mục sản phẩm: ");
            String catalog = sc.nextLine().trim();
            System.out.print("Nhập trạng thái sản phẩm (Nhập 1: Mở bán, 0: Khóa): ");
            String status = sc.nextLine().trim();

            // Ràng buộc 1: Không để trống thông tin bắt buộc
            if (name.isEmpty() || title.isEmpty() || catalog.isEmpty() || status.isEmpty()) {
                System.out.println("❌ Lỗi: Các trường dữ liệu chuỗi không được để trống!");
                return null;
            }
            // Ràng buộc 2: Giá sản phẩm phải lớn hơn 0
            if (price <= 0) {
                System.out.println("❌ Lỗi validate: Giá bán sản phẩm bắt buộc phải lớn hơn 0!");
                return null;
            }
            // Ràng buộc 3: Trạng thái bit phải là 0 hoặc 1
            if (!status.equals("0") && !status.equals("1")) {
                System.out.println("❌ Lỗi validate: Trạng thái sản phẩm chỉ cho phép nhập ký tự '0' hoặc '1'!");
                return null;
            }

            Date currentDate = new Date(System.currentTimeMillis()); // Tự động lấy ngày hiện tại hệ thống tạo sản phẩm

            if (id == null) {
                return new Product(name, price, title, currentDate, catalog, status);
            } else {
                return new Product(id, name, price, title, currentDate, catalog, status);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi dữ liệu nhập: Giá sản phẩm phải nhập đúng định dạng số!");
            return null;
        }
    }
}