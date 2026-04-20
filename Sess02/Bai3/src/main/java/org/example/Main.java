package org.example;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập vào một số nguyên N: ");
        long n = scanner.nextLong();

        long originalN = n;
        n = Math.abs(n);

        long sum = 0;
        long tempN = n;

        while (tempN > 0) {
            long lastDigit = tempN % 10;
            sum += lastDigit;
            tempN = tempN / 10;
        }
        System.out.println("Tổng các chữ số của " + originalN + " là: " + sum);
        scanner.close();
    }
}
