package org.example;

import java.util.Scanner;
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tử số 1 (a): ");
        int a = scanner.nextInt();
        System.out.print("Nhập mẫu số 1 (b): ");
        int b = scanner.nextInt();

        System.out.print("Nhập tử số 2 (c): ");
        int c = scanner.nextInt();
        System.out.print("Nhập mẫu số 2 (d): ");
        int d = scanner.nextInt();

        int tuTong = (a * d) + (b * c);
        int mauTong = b * d;

        System.out.println("\n--- Kết quả ---");
        System.out.println("Tổng hai phân số là: " + tuTong + "/" + mauTong);

        scanner.close();
    }
}
