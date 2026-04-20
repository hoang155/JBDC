package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Chương trình phân loại tam giác ---");
        System.out.print("Nhập cạnh a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhập cạnh b: ");
        double b = scanner.nextDouble();
        System.out.print("Nhập cạnh c: ");
        double c = scanner.nextDouble();

        if (a + b > c && a + c > b && b + c > a) {

            if (a == b && b == c) {
                System.out.println("Đây là tam giác đều");
            }
            else if (a == b || b == c || a == c) {
                System.out.println("Đây là tam giác cân");
            }
            else if (isRightTriangle(a, b, c)) {
                System.out.println("Đây là tam giác vuông");
            }
            else {
                System.out.println("Đây là tam giác thường");
            }

        } else {
            System.out.println("Ba cạnh không tạo thành tam giác.");
        }
        scanner.close();
    }

    public static boolean isRightTriangle(double a, double b, double c) {
        return (a * a + b * b == c * c) ||
                (a * a + c * c == b * b) ||
                (b * b + c * c == a * a);
    }
}
