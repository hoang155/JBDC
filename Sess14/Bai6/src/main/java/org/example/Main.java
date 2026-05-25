package org.example;

import entity.Product;
import entity.ShoppingCart;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static List<Product> danhSachSanPham = new ArrayList<>();
    private static ShoppingCart gioHang = new ShoppingCart();
    static void main() {
        Scanner scanner = new Scanner(System.in);

        // Khởi tạo một số sản phẩm mặc định có sẵn trong hệ thống
        danhSachSanPham.add(new Product("P001", "Điện thoại iPhone 15", 22000000));
        danhSachSanPham.add(new Product("P002", "Laptop ASUS ROG", 35000000));
        danhSachSanPham.add(new Product("P003", "Tai nghe Sony WH", 6500000));

        int luaChon = -1;

        while (luaChon != 0) {
            System.out.println("\n============ MENU ============");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm vào giỏ hàng");
            System.out.println("3. Xóa sản phẩm khỏi giỏ hàng");
            System.out.println("4. Xem giỏ hàng");
            System.out.println("5. Thanh toán");
            System.out.println("0. Thoát");
            System.out.println("==============================");
            System.out.print("Lựa chọn của bạn: ");

            try {
                luaChon = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng trống sau khi nhận số int

                switch (luaChon) {
                    case 1:
                        System.out.println("\n--- DANH SÁCH SẢN PHẨM CÓ SẴN ---");
                        for (Product p : danhSachSanPham) {
                            System.out.println(p);
                        }
                        break;

                    case 2:
                        System.out.print("Nhập mã sản phẩm muốn mua: ");
                        String maThem = scanner.nextLine().trim();
                        Product spThem = findProductById(maThem);

                        if (spThem == null) {
                            System.out.println("Lỗi: Mã sản phẩm không tồn tại trên hệ thống!");
                        } else {
                            System.out.print("Nhập số lượng muốn mua: ");
                            // Bắt lỗi nhập số lượng không phải là số
                            int soLuong = scanner.nextInt();
                            scanner.nextLine();

                            // Bắt lỗi nghiệp vụ số lượng âm hoặc bằng 0
                            if (soLuong <= 0) {
                                throw new IllegalArgumentException("Lỗi: Số lượng không hợp lệ!");
                            }
                            gioHang.addToCart(spThem, soLuong);
                        }
                        break;

                    case 3:
                        System.out.print("Nhập mã sản phẩm muốn xóa khỏi giỏ: ");
                        String maXoa = scanner.nextLine().trim();
                        gioHang.removeFromCart(maXoa); // Phương thức này tự ném lỗi nếu không tìm thấy mã
                        break;

                    case 4:
                        gioHang.displayCart();
                        break;

                    case 5:
                        gioHang.checkout();
                        break;

                    case 0:
                        System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 0 đến 5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Số lượng không hợp lệ!");
                scanner.nextLine(); // Clear bộ nhớ đệm tránh vòng lặp vô tận
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // In các thông báo lỗi nghiệp vụ tùy biến
            }
        }
        scanner.close();
    }

    // Hàm trợ giúp tìm sản phẩm hệ thống bằng ID
    private static Product findProductById(String id) {
        for (Product p : danhSachSanPham) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}
