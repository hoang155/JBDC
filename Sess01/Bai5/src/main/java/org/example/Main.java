package org.example;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập cân nặng (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Nhập chiều cao (m): ");
        double height = scanner.nextDouble();

        double bmi = weight / (height * height);

        System.out.printf("Chỉ số BMI = %.2f%n", bmi);

        scanner.close();
    }
}
