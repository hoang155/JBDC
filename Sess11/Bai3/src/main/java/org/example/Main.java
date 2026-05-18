package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Employee[] employees = new Employee[3];
        employees[0] = new FullTimeEmployee(101, "Nguyễn Văn A", 15000000);
        employees[1] = new PartTimeEmployee(102, "Trần Thị B", 80, 50000);
        employees[2] = new FullTimeEmployee(103, "Lê Hoàng C", 20000000);

        System.out.println("===== HỆ THỐNG QUẢN LÝ LƯƠNG NHÂN VIÊN =====");

        for (Employee emp : employees) {
            emp.showInfo();
            System.out.println();

            double salary = emp.calculateSalary();
            System.out.println("   -> Lương nhận: " + salary + " VND");

            if (emp instanceof BonusEligible) {
                BonusEligible bonusEmp = (BonusEligible) emp;
                double bonus = bonusEmp.calculateBonus();
                System.out.println("   -> Thưởng cuối tháng (10%): " + bonus + " VND");
            } else {
                System.out.println("   -> Thưởng cuối tháng: Không có thưởng (Nhân viên Part-time)");
            }

            System.out.println("--------------------------------------------");
        }
    }
}
