package org.example;

import entity.Order;
import entity.Product;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Sử dụng List để quản lý danh sách sản phẩm của cửa hàng
    private static List<Product> storeProducts = new ArrayList<>();
    // Sử dụng Map để quản lý đơn hàng bằng mã đơn (Dùng String làm Key để linh hoạt)
    private static Map<String, Order> orderMap = new HashMap<>();
    static void main() {
        Scanner scanner = new Scanner(System.in);

        // Dữ liệu mẫu ban đầu cho cửa hàng
        storeProducts.add(new Product(1, "Bánh mì", 15000));
        storeProducts.add(new Product(2, "Cà phê sữa", 25000));

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n================ MENU ================");
            System.out.println("1. Thêm sản phẩm vào cửa hàng");
            System.out.println("2. Xóa sản phẩm khỏi cửa hàng");
            System.out.println("3. Hiển thị danh sách sản phẩm");
            System.out.println("4. Tạo đơn hàng mới");
            System.out.println("5. Thêm sản phẩm vào đơn hàng");
            System.out.println("6. Hiển thị thông tin đơn hàng");
            System.out.println("0. Thoát");
            System.out.println("======================================");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Xử lý trôi lệnh

                switch (choice) {
                    case 1: // Thêm sản phẩm
                        System.out.print("Nhập ID sản phẩm: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nhập tên sản phẩm: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Nhập giá sản phẩm: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();

                        // Xử lý ngoại lệ nghiệp vụ: Giá sản phẩm <= 0
                        if (price <= 0) {
                            throw new IllegalArgumentException("Lỗi: Giá sản phẩm phải lớn hơn 0!");
                        }

                        storeProducts.add(new Product(id, name, price));
                        System.out.println("Đã thêm sản phẩm thành công.");
                        break;

                    case 2: // Xóa sản phẩm theo ID
                        System.out.print("Nhập ID sản phẩm cần xóa: ");
                        int idXoa = scanner.nextInt();
                        scanner.nextLine();

                        boolean checkXoa = storeProducts.removeIf(p -> p.getId() == idXoa);
                        // Xử lý ngoại lệ nghiệp vụ: Xóa sản phẩm không tồn tại
                        if (!checkXoa) {
                            throw new IllegalArgumentException("Lỗi: Không tìm thấy sản phẩm có ID này trong hệ thống!");
                        }
                        System.out.println("Đã xóa sản phẩm thành công.");
                        break;

                    case 3: // Hiển thị sản phẩm cửa hàng
                        if (storeProducts.isEmpty()) {
                            System.out.println("Cửa hàng hiện chưa có sản phẩm nào.");
                        } else {
                            System.out.println("--- DANH SÁCH SẢN PHẨM ---");
                            for (Product p : storeProducts) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 4: // Tạo đơn hàng
                        System.out.print("Thiết lập mã đơn hàng (Ví dụ: DH01): ");
                        String codeOrder = scanner.nextLine().trim();
                        System.out.print("Nhập số thứ tự ID đơn (số nguyên): ");
                        int orderId = scanner.nextInt();
                        scanner.nextLine();

                        if (orderMap.containsKey(codeOrder)) {
                            System.out.println("Mã đơn hàng này đã tồn tại!");
                        } else {
                            orderMap.put(codeOrder, new Order(orderId));
                            System.out.println("Đã tạo đơn hàng trống mang mã [" + codeOrder + "] thành công.");
                        }
                        break;

                    case 5: // Thêm sản phẩm vào đơn hàng
                        System.out.print("Nhập mã đơn hàng muốn thêm SP: ");
                        String keyOrder = scanner.nextLine().trim();

                        // Xử lý ngoại lệ nghiệp vụ: Truy cập đơn hàng không tồn tại
                        if (!orderMap.containsKey(keyOrder)) {
                            throw new IllegalArgumentException("Lỗi: Đơn hàng mang mã " + keyOrder + " không tồn tại!");
                        }

                        System.out.print("Nhập ID sản phẩm của cửa hàng muốn mua: ");
                        int idSpMua = scanner.nextInt();
                        scanner.nextLine();

                        Product spSelected = null;
                        for (Product p : storeProducts) {
                            if (p.getId() == idSpMua) {
                                spSelected = p;
                                break;
                            }
                        }

                        // Xử lý ngoại lệ nghiệp vụ: Thêm sản phẩm không tồn tại
                        if (spSelected == null) {
                            throw new IllegalArgumentException("Lỗi: Không tìm thấy sản phẩm có ID [" + idSpMua + "] trong cửa hàng!");
                        }

                        // Lấy đơn hàng từ Map ra và thêm sản phẩm vào
                        Order currentOrder = orderMap.get(keyOrder);
                        currentOrder.addProduct(spSelected);
                        System.out.println("Đã thêm sản phẩm " + spSelected.getName() + " vào đơn hàng " + keyOrder);
                        break;

                    case 6: // Hiển thị đơn hàng
                        System.out.print("Nhập mã đơn hàng cần xem: ");
                        String keyXem = scanner.nextLine().trim();

                        if (!orderMap.containsKey(keyXem)) {
                            throw new IllegalArgumentException("Lỗi: Đơn hàng không tồn tại!");
                        }

                        Order orderView = orderMap.get(keyXem);
                        System.out.println("\n--- THÔNG TIN ĐƠN HÀNG: " + keyXem + " (Số ID: " + orderView.getOrderId() + ") ---");
                        if (orderView.getProducts().isEmpty()) {
                            System.out.println(" Đơn hàng này hiện chưa chọn mua sản phẩm nào.");
                        } else {
                            for (Product p : orderView.getProducts()) {
                                System.out.println(" + " + p.getName() + " | Giá: " + p.getPrice());
                            }
                            System.out.println("------------------------------------");
                            System.out.printf(" TỔNG TIỀN ĐƠN HÀNG: %,.0f đ\n", orderView.tinhTongTien());
                        }
                        break;

                    case 0:
                        System.out.println("Hệ thống đóng. Tạm biệt!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại từ 0 đến 6.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Bạn nhập sai kiểu dữ liệu định dạng số!");
                scanner.nextLine(); // Giải phóng bộ đệm Scanner
            } catch (IllegalArgumentException e) {
                // In ra chi tiết các lỗi vi phạm điều kiện nghiệp vụ
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
