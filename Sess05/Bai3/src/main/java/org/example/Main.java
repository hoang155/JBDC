package org.example;

import java.util.Scanner;
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mật khẩu cần kiểm tra: ");
        String password = sc.nextLine();
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!%]).{8,}$";

        // Kiểm tra tính hợp lệ
        if (password.matches(regex)) {
            System.out.println("Mật khẩu hợp lệ");
        } else {
            System.out.println("Mật khẩu không hợp lệ");
        }

        sc.close();
    }
}
