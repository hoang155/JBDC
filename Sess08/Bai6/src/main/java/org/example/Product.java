package org.example;

import java.util.Scanner;

public class Product {
    // 1. Thuộc tính thông thường
    private int id;
    private String name;
    private double price;

    // 2. Thuộc tính static: Quản lý mã tự tăng
    private static int AUTO_ID = 1;

    // 3. Thuộc tính final: Hằng số mã kho
    public final String WAREHOUSE_CODE = "KHO-01";

    // 4. Constructor không tham số
    public Product() {
        // Tự gán ID và tăng biến đếm lên cho sản phẩm sau
        this.id = AUTO_ID++;
    }

    // 5. Constructor có tham số
    public Product(String name, double price) {
        this(); // Gọi constructor không tham số để lấy ID tự tăng
        this.name = name;
        this.price = price;
    }

    // 6. Getter cho thuộc tính price (để lọc theo khoảng giá)
    public double getPrice() {
        return price;
    }

    // 7. Phương thức nhập thông tin (không nhập ID vì ID tự tăng)
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên sản phẩm: ");
        this.name = sc.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        this.price = Double.parseDouble(sc.nextLine());
    }

    // 8. Phương thức in thông tin
    public void print() {
        System.out.printf("[%s] ID: %d | Tên: %-15s | Giá: %.2f%n",
                WAREHOUSE_CODE, id, name, price);
    }

    // Phương thức static để lấy số lượng sản phẩm đã tạo
    public static int getCount() {
        return AUTO_ID - 1;
    }
}