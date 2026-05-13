package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> productList = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n===== MENU SẢN PHẨM =====");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. In danh sách sản phẩm");
            System.out.println("3. Tìm sản phẩm theo khoảng giá");
            System.out.println("4. Thống kê số sản phẩm đã tạo");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    Product p = new Product();
                    p.input();
                    productList.add(p);
                    System.out.println("Thêm thành công!");
                    break;

                case 2:
                    System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
                    for (Product prod : productList) {
                        prod.print();
                    }
                    break;

                case 3:
                    System.out.print("Nhập giá thấp nhất: ");
                    double min = Double.parseDouble(sc.nextLine());
                    System.out.print("Nhập giá cao nhất: ");
                    double max = Double.parseDouble(sc.nextLine());

                    System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
                    for (Product prod : productList) {
                        if (prod.getPrice() >= min && prod.getPrice() <= max) {
                            prod.print();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Tổng số sản phẩm đã được tạo trong hệ thống: " + Product.getCount());
                    break;

                case 0:
                    System.out.println("Đang thoát chương trình...");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}