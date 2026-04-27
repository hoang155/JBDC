package org.example;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập địa chỉ email: ");
        String input = sc.nextLine();

        String email = input.trim();

        String regex = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,6}$";

        if (email.matches(regex)) {
            System.out.println("Email hợp lệ");
        } else {
            System.out.println("Email không hợp lệ");
        }

        sc.close();
    }
}
