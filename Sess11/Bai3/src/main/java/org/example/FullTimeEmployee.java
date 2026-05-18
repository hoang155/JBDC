package org.example;

public class FullTimeEmployee extends Employee implements BonusEligible {
    private double baseSalary;

    // Constructor gọi lên constructor của lớp cha bằng super()
    public FullTimeEmployee(int id, String name, double baseSalary) {
        super(id, name);
        this.baseSalary = baseSalary;
    }

    // Ghi đè phương thức tính lương của lớp cha
    @Override
    public double calculateSalary() {
        return baseSalary;
    }

    // Ghi đè phương thức tính thưởng của interface
    @Override
    public double calculateBonus() {
        return baseSalary * 0.10;
    }

    // Tùy chọn: Ghi đè showInfo() để in thêm thông tin lương cơ bản
    @Override
    public void showInfo() {
        super.showInfo(); // Gọi logic in ID và Tên của lớp cha
        System.out.print(" | Loại: Full-time | Lương cơ bản: " + baseSalary + " VND");
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}
