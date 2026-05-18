package org.example;

import java.util.Scanner;

public abstract class Staff {
    private int id;
    private String name;
    private double baseSalary;
    public Staff() {
    }

    public Staff(int id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    public abstract double calculateTotalSalary();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên nhân viên: ");
        this.name = scanner.nextLine().trim();

        while (true) {
            System.out.print("Nhập lương cơ bản: ");
            try {
                this.baseSalary = Double.parseDouble(scanner.nextLine().trim());
                if (this.baseSalary < 0) {
                    System.out.println("❌ Lỗi: Lương cơ bản không được âm!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Lương cơ bản phải là số thực hợp lệ!");
            }
        }
    }
    public void displayData() {
        System.out.printf("ID: %-4d | Tên: %-20s | Lương cứng: %,.2f VND", id, name, baseSalary);
    }
}
