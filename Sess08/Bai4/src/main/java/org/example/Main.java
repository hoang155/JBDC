package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Rectangle r1 = new Rectangle(3, 4);
        Rectangle r2 = new Rectangle(5, 2);
        Rectangle r3 = new Rectangle(4.5, 3.5);

        // In thông tin các hình
        System.out.println("Thông tin các hình chữ nhật:");
        System.out.println("r1: " + r1.toString());
        System.out.println("r2: " + r2.toString());
        System.out.println("r3: " + r3.toString());

        System.out.println("\n--- So sánh diện tích ---");

        // Tìm diện tích lớn nhất
        double maxArea = Math.max(r1.getArea(), Math.max(r2.getArea(), r3.getArea()));

        // Đếm xem có bao nhiêu hình có diện tích bằng maxArea
        int count = 0;
        Rectangle[] rectangles = {r1, r2, r3};

        for (Rectangle r : rectangles) {
            if (r.getArea() == maxArea) {
                count++;
            }
        }

        // Kiểm tra và in kết quả theo yêu cầu
        if (count > 1) {
            System.out.println("Có nhiều hình có diện tích lớn nhất bằng nhau: " + maxArea);
        } else {
            System.out.print("Hình có diện tích lớn nhất là: ");
            if (r1.getArea() == maxArea) System.out.println(r1.toString());
            else if (r2.getArea() == maxArea) System.out.println(r2.toString());
            else System.out.println(r3.toString());
        }
    }
}
