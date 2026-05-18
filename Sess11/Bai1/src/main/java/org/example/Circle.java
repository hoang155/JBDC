package org.example;

public class Circle extends Shape implements Drawable {
    private double radius;
    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }
    @Override
    public void draw() {}

    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }

    @Override
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public void displayInfo() {
        System.out.println("Thực hiện: Đã vẽ hình tròn " + name);
    }
}
