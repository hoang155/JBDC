package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhập vào một số nguyên dương: ");
            int n = scanner.nextInt();

            // Xử lý trường hợp nhập số không hợp lệ (<= 0)
            if (n <= 0) {
                System.out.println("Lỗi: Số nhập vào không hợp lệ để kiểm tra số nguyên tố (phải lớn hơn 0).");
            } else {
                // Nếu dữ liệu hợp lệ, tiến hành kiểm tra số nguyên tố
                if (isPrime(n)) {
                    System.out.println(n + " là số nguyên tố.");
                } else {
                    System.out.println(n + " không phải là số nguyên tố.");
                }
            }

        } catch (InputMismatchException e) {
            // Xử lý ngoại lệ khi nhập không phải số nguyên (chữ, ký tự đặc biệt, số thực)
            System.out.println("Lỗi định dạng: Dữ liệu nhập vào không phải là một số nguyên hợp lệ!");
        } finally {
            scanner.close(); // Đóng tài nguyên scanner
        }
    }

    /**
     * Hàm kiểm tra số nguyên tố
     */
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false; // Chia hết cho số khác ngoài 1 và chính nó
            }
        }
        return true;
    }
}
