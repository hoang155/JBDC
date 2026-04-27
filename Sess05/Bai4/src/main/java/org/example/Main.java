package org.example;

import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // 1. Nhận đầu vào n
        System.out.print("Nhập độ dài chuỗi n (1 <= n <= 1000): ");
        int n = sc.nextInt();

        if (n < 1 || n > 1000) {
            System.out.println("Độ dài không hợp lệ!");
            return;
        }

        // 2. Định nghĩa tập hợp các ký tự cho phép
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // 3. Sử dụng StringBuilder để tối ưu hiệu suất
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // Lấy một chỉ số ngẫu nhiên từ 0 đến độ dài của chuỗi characters
            int index = random.nextInt(characters.length());

            // Lấy ký tự tại vị trí đó và thêm vào chuỗi kết quả
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        // 4. Trả về kết quả
        System.out.println("Chuỗi ngẫu nhiên được tạo ra là:");
        System.out.println(sb.toString());

        sc.close();
    }
}
