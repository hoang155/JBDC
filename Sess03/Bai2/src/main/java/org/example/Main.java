package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        // Các biến lưu trữ thống kê
        int count = 0;
        double sum = 0;
        double max = -1; // Khởi tạo thấp hơn mức tối thiểu
        double min = 11; // Khởi tạo cao hơn mức tối đa

        while (true) {
            // Hiển thị Menu
            System.out.println("\n****************** MENU NHẬP ĐIỂM ******************");
            System.out.println("1.    Nhập điểm học viên");
            System.out.println("2.    Hiển thị thống kê");
            System.out.println("3.    Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("--- Nhập điểm (Nhập -1 để dừng) ---");
                    while (true) {
                        System.out.print("Nhập điểm: ");
                        double score = scanner.nextDouble();

                        if (score == -1) break;

                        if (score < 0 || score > 10) {
                            System.out.println("Điểm không hợp lệ! Vui lòng nhập lại.");
                            continue;
                        }

                        // Xử lý thống kê
                        count++;
                        sum += score;
                        if (score > max) max = score;
                        if (score < min) min = score;

                        // Xếp loại học lực
                        String rank = "";
                        if (score < 5) rank = "Yếu";
                        else if (score < 7) rank = "Trung Bình";
                        else if (score < 8) rank = "Khá";
                        else if (score < 9) rank = "Giỏi";
                        else rank = "Xuất sắc";

                        System.out.println("=> Xếp loại: " + rank);
                    }
                    break;

                case 2:
                    System.out.println("\n--- KẾT QUẢ THỐNG KÊ ---");
                    if (count == 0) {
                        System.out.println("Chưa có dữ liệu!");
                    } else {
                        System.out.println("Số học viên đã nhập: " + count);
                        System.out.println("Tổng điểm: " + sum);
                        System.out.println("Điểm trung bình: " + (sum / count));
                        System.out.println("Điểm cao nhất: " + max);
                        System.out.println("Điểm thấp nhất: " + min);
                    }
                    break;

                case 3:
                    System.out.println("Đang thoát chương trình... Tạm biệt!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn 1, 2 hoặc 3.");
            }
        }
    }
}
