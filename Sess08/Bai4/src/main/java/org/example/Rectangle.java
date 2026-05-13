package org.example;

public class Rectangle {
    // 1. Thuộc tính (private để đảm bảo tính đóng gói)
    private double width;
    private double height;

    // 2. Constructor có tham số
    public Rectangle(double width, double height) {
        this.width = width;   // 'this.width' là thuộc tính của lớp
        this.height = height; // 'height' là tham số truyền vào
    }

    // 3. Các phương thức getter
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // 4. Các phương thức tính toán
    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    // 5. Phương thức toString để mô tả đối tượng
    @Override
    public String toString() {
        return String.format("Rectangle(width=%.1f, height=%.1f, area=%.1f, perimeter=%.1f)",
                width, height, getArea(), getPerimeter());
    }
}