package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner input = new Scanner(System.in);

        // Nhập dữ liệu từ bàn phím
        System.out.print("Nhập chiều rộng: ");
        double w = input.nextDouble();
        System.out.print("Nhập chiều cao: ");
        double h = input.nextDouble();

        // 1. Tạo 1 object Rectangle thông qua constructor
        Rectangle rect = new Rectangle(w, h);

        // 2. Gọi các phương thức và in kết quả
        System.out.println("\n--- Kết quả ---");

        input.close();
    }
}
