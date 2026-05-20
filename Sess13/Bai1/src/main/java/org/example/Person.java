package org.example;

import java.util.Scanner;

public class Person {
    private String name;
    private String email;
    private String phone;
    public Person(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void inputData(Scanner scanner) {
        // 1. Nhập tên người dùng
        while (true) {
            System.out.println("Nhập tên người dùng: ");
            this.name = scanner.nextLine().trim();
            if (this.name.isEmpty()) {
                System.out.println("\nVui lòng ko để trống !");
                continue;
            }
            break;
        }

        // 2. Nhập email người dùng
        while (true) {
            System.out.println("Nhập email người dùng: ");
            this.email = scanner.nextLine().trim();
            if (this.email.isEmpty()) {
                System.out.println("\nVui lòng ko để trống !");
                continue;
            }
            break;
        }

        // 3. Nhập số điện thoại
        while (true) {
            System.out.println("Nhập số điện thoại người dùng: ");
            this.phone = scanner.nextLine().trim();
            if (this.phone.isEmpty()) {
                System.out.println("\nVui lòng ko để trống !");
                continue;
            }
            break;
        }
    }

    // Hiển thị thông tin người dùng
    public void displayData() {
        System.out.printf("Tên: %-20s | Email: %-25s | SĐT: %s\n", name, email, phone);
    }
}
