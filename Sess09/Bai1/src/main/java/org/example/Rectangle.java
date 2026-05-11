package org.example;

public class Rectangle {
    // Khai báo 2 thuộc tính private
    private double width;
    private double height;

    // Constructor để set giá trị khi tạo đối tượng
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Phương thức tính diện tích
    public double getArea() {
        return width * height;
    }

    // Phương thức tính chu vi
    public double getPerimeter() {
        return (width + height) * 2;
    }

    // Phương thức in thông tin (khuyến khích)
    public void printInfo() {
        System.out.printf("Hình chữ nhật [Rộng: %.1f, Cao: %.1f]%n", width, height);
        System.out.printf("- Diện tích: %.1f%n", getArea());
        System.out.printf("- Chu vi: %.1f%n", getPerimeter());
    }
}