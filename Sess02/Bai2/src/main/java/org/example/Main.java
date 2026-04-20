package org.example;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập vào một số nguyên từ 1 đến 12: ");
        int month = scanner.nextInt();
        // Sử dụng switch-case để kiểm tra tháng
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                System.out.println("Tháng " + month + " có 31 ngày.");
                break;

            case 4: case 6: case 9: case 11:
                System.out.println("Tháng " + month + " có 30 ngày.");
                break;

            case 2:
                System.out.println("Tháng 2 có 28 hoặc 29 ngày.");
                break;

            default:
                System.out.println("Tháng không hợp lệ.");
                break;
        }

        scanner.close();
    }
}
