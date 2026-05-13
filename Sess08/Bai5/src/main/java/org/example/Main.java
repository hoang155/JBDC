package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n===== MENU SINH VIÊN =====");
            System.out.println("1. Nhập danh sách sinh viên");
            System.out.println("2. In danh sách sinh viên");
            System.out.println("3. Tìm sinh viên GPA cao nhất");
            System.out.println("4. In tổng số sinh viên đã tạo");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng sinh viên muốn thêm: ");
                    int n = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < n; i++) {
                        System.out.println("Nhập sinh viên thứ " + (i + 1) + ":");
                        Student s = new Student();
                        s.input();
                        list.add(s);
                    }
                    break;

                case 2:
                    if (list.isEmpty()) {
                        System.out.println("Danh sách trống!");
                    } else {
                        System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
                        for (Student s : list) {
                            s.print();
                        }
                    }
                    break;

                case 3:
                    if (list.isEmpty()) {
                        System.out.println("Danh sách trống!");
                    } else {
                        double maxGpa = list.get(0).getGpa();
                        for (Student s : list) {
                            if (s.getGpa() > maxGpa) maxGpa = s.getGpa();
                        }
                        System.out.println("\n--- Sinh viên có GPA cao nhất (" + maxGpa + ") ---");
                        for (Student s : list) {
                            if (s.getGpa() == maxGpa) s.print();
                        }
                    }
                    break;

                case 4:
                    // Truy cập trực tiếp qua tên lớp vì là phương thức static
                    System.out.println("Tổng số sinh viên hiện có: " + Student.getTotalStudent());
                    break;

                case 0:
                    System.out.println("Tạm biệt!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
