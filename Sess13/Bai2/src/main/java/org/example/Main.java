package org.example;

import entity.AttendanceManager;
import entity.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n******************** MENU QUẢN LÝ ĐIỂM DANH ********************");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị danh sách sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Nhập id sinh viên : ");
                    int id = 0;
                    try {
                        id = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Lỗi: ID phải là số nguyên!");
                        break;
                    }

                    // Kiểm tra trùng lặp ID trước khi thêm mới
                    if (manager.findIndexById(id) != -1) {
                        System.out.println("❌ Lỗi: ID sinh viên này đã tồn tại trong danh sách!");
                        break;
                    }

                    System.out.println("Nhập tên sinh viên: ");
                    String name = scanner.nextLine().trim();

                    Student newStudent = new Student(id, name);
                    manager.add(newStudent);
                    break;

                case "2":
                    if (manager.isEmpty()) {
                        System.out.println("⚠️ Danh sách trống, không có sinh viên nào để sửa.");
                        break;
                    }
                    // Hiển thị danh sách hiện có trước khi sửa giống ảnh mẫu
                    manager.display();

                    System.out.print("Nhập id sinh viên cần sửa: ");
                    int updateId = 0;
                    try {
                        updateId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Lỗi: ID phải là số nguyên!");
                        break;
                    }

                    int updateIndex = manager.findIndexById(updateId);
                    if (updateIndex == -1) {
                        System.out.println("❌ Không tìm thấy sinh viên có ID: " + updateId);
                    } else {
                        System.out.println("Nhập tên mới sinh viên: ");
                        String newName = scanner.nextLine().trim();

                        // Cập nhật lại thông tin đối tượng tại index tìm thấy
                        Student updatedStudent = new Student(updateId, newName);
                        manager.update(updateIndex, updatedStudent);
                    }
                    break;

                case "3":
                    if (manager.isEmpty()) {
                        System.out.println("⚠️ Danh sách trống, không có sinh viên nào để xóa.");
                        break;
                    }
                    // Hiển thị danh sách hiện có trước khi xóa giống ảnh mẫu
                    manager.display();

                    System.out.print("Nhập id sinh viên cần xóa: ");
                    int deleteId = 0;
                    try {
                        deleteId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Lỗi: ID phải là số nguyên!");
                        break;
                    }

                    int deleteIndex = manager.findIndexById(deleteId);
                    if (deleteIndex == -1) {
                        System.out.println("❌ Không tìm thấy sinh viên có ID: " + deleteId);
                    } else {
                        manager.delete(deleteIndex);
                    }
                    break;

                case "4":
                    manager.display();
                    break;

                case "5":
                    System.out.println("👋 Đang đóng chương trình quản lý điểm danh. Tạm biệt!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ! Vui lòng chọn lại từ 1 đến 5.");
            }
        }
    }
}