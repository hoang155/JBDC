package ra.entity;

import java.util.Scanner;

public abstract class Drink implements IPromotion {
    private int id;
    private String name;
    private double price;

    public Drink() {
    }

    public Drink(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Phương thức trừu tượng quy định cách pha chế
    public abstract void prepare();

    // Triển khai phương thức từ Interface IPromotion
    @Override
    public void applyDiscount(double percentage) {
        this.price = this.price * (1 - percentage / 100);
    }

    // Nhập dữ liệu chung
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên đồ uống: ");
        this.name = scanner.nextLine().trim();

        while (true) {
            System.out.print("Nhập giá bán (VND): ");
            try {
                this.price = Double.parseDouble(scanner.nextLine().trim());
                if (this.price <= 0) {
                    System.out.println("❌ Lỗi: Giá bán phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Giá bán phải là một số thực hợp lệ!");
            }
        }
    }

    // Hiển thị dữ liệu chung
    public void displayData() {
        System.out.printf("ID: %-4d | Tên món: %-18s | Giá: %,10.2f VND | Pha chế: ", id, name, price);
        prepare(); // Gọi phương thức đa hình của lớp con để in cách pha chế tương ứng
    }

    // Getters và Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}