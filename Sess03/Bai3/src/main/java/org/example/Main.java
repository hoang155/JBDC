package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        // Các biến lưu trữ thống kê
        int totalEmployees = 0;
        double totalSalary = 0;
        double totalBonus = 0;
        double maxSalary = -1;
        double minSalary = 600000000; // Khởi tạo cao hơn mức 500 triệu

        while (true) {
            System.out.println("\n****************** MENU NHẬP LƯƠNG ******************");
            System.out.println("1.    Nhập lương nhân viên");
            System.out.println("2.    Hiển thị thống kê");
            System.out.println("3.    Tính tổng số tiền thưởng cho nhân viên");
            System.out.println("4.    Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("--- Nhập lương (đơn vị: triệu VNĐ, Nhập -1 để dừng) ---");
                    while (true) {
                        System.out.print("Nhập lương: ");
                        double salary = scanner.nextDouble();

                        if (salary == -1) break;

                        if (salary < 0 || salary > 500) {
                            System.out.println("Lương không hợp lệ! Vui lòng nhập từ 0 đến 500 triệu.");
                            continue;
                        }

                        // Cập nhật thống kê cơ bản
                        totalEmployees++;
                        totalSalary += salary;
                        if (salary > maxSalary) maxSalary = salary;
                        if (salary < minSalary) minSalary = salary;

                        // Tính thưởng tích lũy cho Chức năng 3
                        totalBonus += calculateBonus(salary);

                        // Phân loại thu nhập
                        String category = "";
                        if (salary < 5) category = "Thu nhập thấp";
                        else if (salary <= 15) category = "Thu nhập trung bình";
                        else if (salary <= 50) category = "Thu nhập khá";
                        else category = "Thu nhập cao";

                        System.out.println("=> Phân loại: " + category);
                    }
                    break;

                case 2:
                    System.out.println("\n--- THỐNG KÊ LƯƠNG ---");
                    if (totalEmployees == 0) {
                        System.out.println("Chưa có dữ liệu!");
                    } else {
                        System.out.println("Số nhân viên đã nhập: " + totalEmployees);
                        System.out.println("Tổng tiền lương: " + totalSalary + " triệu");
                        System.out.println("Lương trung bình: " + (totalSalary / totalEmployees) + " triệu");
                        System.out.println("Lương cao nhất: " + maxSalary + " triệu");
                        System.out.println("Lương thấp nhất: " + minSalary + " triệu");
                    }
                    break;

                case 3:
                    System.out.println("\n--- TỔNG TIỀN THƯỞNG ---");
                    if (totalEmployees == 0) {
                        System.out.println("Chưa có dữ liệu để tính thưởng!");
                    } else {
                        System.out.println("Tổng số tiền thưởng phải chi: " + totalBonus + " triệu VNĐ");
                    }
                    break;

                case 4:
                    System.out.println("Đang thoát... Tạm biệt HoangPK!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public static double calculateBonus(double salary) {
        if (salary <= 5) return salary * 0.05;
        if (salary <= 15) return salary * 0.10;
        if (salary <= 50) return salary * 0.15;
        if (salary <= 100) return salary * 0.20;
        return salary * 0.25;
    }
}
