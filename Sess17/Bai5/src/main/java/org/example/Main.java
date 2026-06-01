package org.example;
import entity.Employee;
import entity.Project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Management manager = new Management();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== COMPANY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Thêm nhân viên mới");
            System.out.println("2. Thêm dự án mới");
            System.out.println("3. Gán nhân viên vào dự án");
            System.out.println("4. Hiển thị danh sách nhân viên & dự án");
            System.out.println("5. Cập nhật lương nhân viên");
            System.out.println("6. Thoát chương trình");
            System.out.print("Vui lòng chọn chức năng (1-6): ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên nhân viên: ");
                        String empName = sc.nextLine().trim();
                        System.out.print("Nhập phòng ban: ");
                        String dept = sc.nextLine().trim();
                        if (empName.isEmpty() || dept.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Tên nhân viên và phòng ban không được trống!");
                            break;
                        }
                        System.out.print("Nhập mức lương: ");
                        double salary = Double.parseDouble(sc.nextLine().trim());
                        if (salary < 0) {
                            System.out.println("⚠️ Lỗi: Mức lương không được là số âm!");
                            break;
                        }
                        manager.addEmployee(new Employee(empName, dept, salary));
                        break;

                    case 2:
                        System.out.print("Nhập tên dự án: ");
                        String projName = sc.nextLine().trim();
                        if (projName.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Tên dự án không được để trống!");
                            break;
                        }
                        System.out.print("Nhập ngân sách dự án (Budget): ");
                        double budget = Double.parseDouble(sc.nextLine().trim());
                        if (budget < 0) {
                            System.out.println("⚠️ Lỗi: Ngân sách dự án không được nhỏ hơn 0!");
                            break;
                        }
                        manager.addProject(new Project(projName, budget));
                        break;

                    case 3:
                        System.out.print("Nhập mã ID nhân viên: ");
                        int empId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập mã ID dự án: ");
                        int projId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập vai trò của nhân viên trong dự án: ");
                        String role = sc.nextLine().trim();

                        if (role.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Vai trò đảm nhiệm không được bỏ trống!");
                            break;
                        }
                        manager.assignEmployeeToProject(empId, projId, role);
                        break;

                    case 4:
                        manager.listEmployeesAndProjects();
                        break;

                    case 5:
                        System.out.print("Nhập mã ID nhân viên cần thay đổi lương: ");
                        int updateEmpId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập mức lương mới: ");
                        double newSalary = Double.parseDouble(sc.nextLine().trim());
                        if (newSalary < 0) {
                            System.out.println("⚠️ Lỗi: Mức lương mới không hợp lệ!");
                            break;
                        }
                        manager.updateEmployeeSalary(updateEmpId, newSalary);
                        break;

                    case 6:
                        System.out.println("Đang tắt hệ thống quản lý công ty. Tạm biệt!");
                        sc.close();
                        return;

                    default:
                        System.out.println("⚠️ Lựa chọn sai mục menu. Vui lòng chọn số từ 1 đến 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi nhập liệu: Yêu cầu nhập đúng định dạng số cho Lựa chọn, ID, Lương hoặc Ngân sách!");
            } catch (Exception e) {
                System.out.println("❌ Đã xảy ra lỗi hệ thống: " + e.getMessage());
            }
        }
    }
}