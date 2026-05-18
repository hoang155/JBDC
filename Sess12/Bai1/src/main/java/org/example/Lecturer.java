package org.example;

import java.util.Scanner;

public class Lecturer extends Staff implements ICapability {
    private int teachingHours;

    public Lecturer() {
    }

    public Lecturer(int id, String name, double baseSalary, int teachingHours) {
        super(id, name, baseSalary);
        this.teachingHours = teachingHours;
    }

    @Override
    public double calculateTotalSalary() {
        return getBaseSalary() + (teachingHours * 200000);
    }

    @Override
    public void inputData(Scanner scanner) {
        super.inputData(scanner); // Nhập name và baseSalary
        while (true) {
            System.out.print("Nhập số giờ dạy: ");
            try {
                this.teachingHours = Integer.parseInt(scanner.nextLine().trim());
                if (this.teachingHours < 0) {
                    System.out.println("❌ Lỗi: Số giờ dạy không được âm!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Số giờ dạy phải là số nguyên!");
            }
        }
    }

    @Override
    public void displayData() {
        super.displayData();
        System.out.printf(" | Giờ dạy: %-3d | Thực nhận: %,.2f VND (Giảng viên)\n",
                teachingHours, calculateTotalSalary());
    }

    @Override
    public void checkPerformance() {
        if (teachingHours >= 60) {
            System.out.println("   -> Đánh giá: Giảng viên dạy vượt chỉ tiêu xuất sắc.");
        } else {
            System.out.println("   -> Đánh giá: Đạt yêu cầu số giờ giảng dạy tối thiểu.");
        }
    }
}
