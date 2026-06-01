package org.example;
import entity.Course;
import entity.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== SCHOOL REGISTRATION SYSTEM ==========");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Thêm khóa học mới");
            System.out.println("3. Ghi danh sinh viên vào khóa học");
            System.out.println("4. Hiển thị danh sách sinh viên & điểm số");
            System.out.println("5. Cập nhật điểm cho sinh viên");
            System.out.println("6. Thoát chương trình");
            System.out.print("Vui lòng chọn chức năng (1-6): ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên sinh viên: ");
                        String stdName = sc.nextLine().trim();
                        System.out.print("Nhập email sinh viên: ");
                        String email = sc.nextLine().trim();
                        if (stdName.isEmpty() || email.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Không được bỏ trống thông tin sinh viên!");
                            break;
                        }
                        manager.addStudent(new Student(stdName, email));
                        break;

                    case 2:
                        System.out.print("Nhập tiêu đề khóa học: ");
                        String title = sc.nextLine().trim();
                        if (title.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Tên tiêu đề khóa học không được trống!");
                            break;
                        }
                        System.out.print("Nhập số tín chỉ (Credits): ");
                        int credits = Integer.parseInt(sc.nextLine().trim());
                        if (credits <= 0) {
                            System.out.println("⚠️ Lỗi: Số lượng tín chỉ phải lớn hơn 0!");
                            break;
                        }
                        manager.addCourse(new Course(title, credits));
                        break;

                    case 3:
                        System.out.print("Nhập mã ID sinh viên: ");
                        int studentId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập mã ID khóa học: ");
                        int courseId = Integer.parseInt(sc.nextLine().trim());
                        manager.enrollStudent(studentId, courseId);
                        break;

                    case 4:
                        manager.listStudentsAndGrades();
                        break;

                    case 5:
                        System.out.print("Nhập mã ID sinh viên cần vào điểm: ");
                        int uStudentId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập mã ID khóa học tương ứng: ");
                        int uCourseId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập điểm số (0.00 đến 10.00): ");
                        double grade = Double.parseDouble(sc.nextLine().trim());

                        if (grade < 0 || grade > 10) {
                            System.out.println("⚠️ Lỗi: Điểm số nhập vào phải nằm trong thang điểm [0 - 10]!");
                            break;
                        }
                        manager.updateStudentGrade(uStudentId, uCourseId, grade);
                        break;

                    case 6:
                        System.out.println("Đang tắt hệ thống quản lý học vụ. Tạm biệt!");
                        sc.close();
                        return;

                    default:
                        System.out.println("⚠️ Lựa chọn mục menu sai, vui lòng nhập lại số từ 1 đến 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi định dạng: Yêu cầu nhập số hợp lệ đối với mục Menu, ID, Tín chỉ hoặc Điểm số!");
            } catch (Exception e) {
                System.out.println("❌ Đã xảy ra lỗi hệ thống: " + e.getMessage());
            }
        }
    }
}