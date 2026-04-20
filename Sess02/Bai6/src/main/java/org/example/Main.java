package org.example;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        int n;

        do {
            System.out.print("Nhập một số nguyên dương N: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Số nhập vào không hợp lệ");
                scanner.next();
            }
            n = scanner.nextInt();
            if (n < 0) {
                System.out.println("Số nhập vào không hợp lệ");
            }
        } while (n < 0);

        System.out.print("Các số Armstrong từ 0 đến " + n + ": ");

        for (int i = 0; i <= n; i++) {
            if (isArmstrong(i)) {
                System.out.print(i + " ");
            }
        }
        scanner.close();
    }

    public static boolean isArmstrong(int number) {
        if (number == 0) return true;

        int k = String.valueOf(number).length();

        int temp = number;
        int sum = 0;

        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, k);
            temp /= 10;
        }

        return sum == number;
    }
}
