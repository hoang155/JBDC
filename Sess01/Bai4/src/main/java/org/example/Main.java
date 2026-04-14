package org.example;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        float width, height;
        float area, perimeter;

        System.out.print("Nhập chiều rộng: ");
        width = scanner.nextFloat();

        System.out.print("Nhập chiều cao: ");
        height = scanner.nextFloat();

        area = width * height;
        perimeter = (width + height) * 2;

        System.out.println("\n--- Kết quả ---");
        System.out.printf("Diện tích hình chữ nhật: %.2f%n", area);
        System.out.printf("Chu vi hình chữ nhật: %.2f%n", perimeter);

        scanner.close();
    }
}
