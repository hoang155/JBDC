package org.example;

import java.util.Scanner;

public class AdminStaff extends Staff implements ICapability {
    private double bonus;

    public AdminStaff() {
    }

    public AdminStaff(int id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateTotalSalary() {
        return getBaseSalary() + bonus;
    }

    @Override
    public void inputData(Scanner scanner) {
        super.inputData(scanner); // Nhập name và baseSalary
        while (true) {
            System.out.print("Nhập tiền thưởng: ");
            try {
                this.bonus = Double.parseDouble(scanner.nextLine().trim());
                if (this.bonus < 0) {
                    System.out.println("❌ Lỗi: Tiền thưởng không được âm!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Tiền thưởng phải là số thực hợp lệ!");
            }
        }
    }

    @Override
    public void displayData() {
        super.displayData();
        System.out.printf(" | Tiền thưởng: %,.2f VND | Thực nhận: %,.2f VND (Hành chính)\n",
                bonus, calculateTotalSalary());
    }

    @Override
    public void checkPerformance() {
        if (bonus > 2000000) {
            System.out.println("   -> Đánh giá: Hoàn thành vượt KPI công việc hành chính.");
        } else {
            System.out.println("   -> Đánh giá: Hoàn thành công việc ở mức bình thường.");
        }
    }
}