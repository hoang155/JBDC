package presentation;

import entity.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Student> studentList = new ArrayList<>();
    private static int autoIncrementId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n==================== MENU ====================");
            System.out.println("1. Nhập danh sách sinh viên");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo tên");
            System.out.println("4. Phân loại sinh viên theo GPA");
            System.out.println("0. Thoát chương trình");
            System.out.println("==============================================");
            System.out.print("Lựa chọn của bạn: ");
            int choice = sc.nextLine().trim();
            switch (choice) {
                case 1:
                    inputStudentList(scanner):
                    break;
                case 2:
                    displayStudentList():
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }
    private static void inputStudentList(Scanner scanner) {
        int count = 0;
        while (true) {
            System.out.print("Nhập số lượng sinh viên muốn thêm: ");
            try {
                count = Integer.parseInt(scanner.nextLine().trim());
                if (count <= 0) {
                    System.out.println("❌ Lỗi: Số lượng sinh viên phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
            }
        }

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Nhập thông tin cho sinh viên thứ " + (i + 1) + " ---");

            String name;
            while (true) {
                System.out.print("Nhập họ và tên sinh viên: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("⚠️ Lỗi: Tên sinh viên không được để trống!");
                    continue;
                }
                break;
            }

            double gpa = 0;
            while (true) {
                System.out.print("Nhập điểm trung bình (GPA 0.0 - 10.0): ");
                try {
                    gpa = Double.parseDouble(scanner.nextLine().trim());
                    if (gpa < 0.0 || gpa > 10.0) {
                        System.out.println("⚠️ Lỗi: Điểm GPA bắt buộc phải nằm trong khoảng từ 0 đến 10!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Lỗi: GPA phải là một số thực hợp lệ!");
                }
            }

            // Tạo đối tượng và tự động gán ID tăng dần
            Student student = new Student(autoIncrementId++, name, gpa);
            studentList.add(student);
        }
        System.out.println("✅ Đã thêm danh sách sinh viên vào hệ thống thành công!");
    }

    // Chức năng 2: In danh sách sinh viên
    private static void displayStudentList() {
        if (studentList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh sách sinh viên hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH SINH VIÊN HIỆN CÓ ---");
        for (Student student : studentList) {
            System.out.println(student); // Tự động gọi phương thức toString() đã ghi đè
        }
    }

    // Chức năng 3: Tìm kiếm sinh viên theo tên
    private static void searchStudentByName(Scanner scanner) {
        if (studentList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh sách trống, không có dữ liệu để tìm kiếm.");
            return;
        }

        System.out.print("Nhập tên sinh viên cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        boolean isFound = false;

        System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
        for (Student student : studentList) {
            // Tìm kiếm tương đối (chứa từ khóa) và không phân biệt chữ hoa chữ thường
            if (student.getName().toLowerCase().contains(keyword)) {
                System.out.println(student);
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("⚠️ Không tìm thấy sinh viên nào phù hợp với tên: " + keyword);
        }
    }

    // Chức năng 4: Phân loại sinh viên theo GPA
    private static void classifyStudentsReport() {
        if (studentList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh sách trống, không có dữ liệu để phân loại.");
            return;
        }

        System.out.println("\n================ BÁO CÁO PHÂN LOẠI SINH VIÊN ================");

        System.out.println("\n[DANH SÁCH XUẤT SẮC (GPA >= 8.5)]");
        printByClassification("Xuất sắc");

        System.out.println("\n[DANH SÁCH GIỎI (7.0 <= GPA < 8.5)]");
        printByClassification("Giỏi");

        System.out.println("\n[DANH SÁCH KHÁ (5.5 <= GPA < 7.0)]");
        printByClassification("Khá");

        System.out.println("\n[DANH SÁCH TRUNG BÌNH / YẾU (GPA < 5.5)]");
        printByClassification("Trung bình / Yếu");

        System.out.println("=============================================================");
    }

    // Hàm hỗ trợ lọc và in sinh viên theo đúng nhóm phân loại cụ thể
    private static void printByClassification(String classificationType) {
        boolean hasStudent = false;
        for (Student student : studentList) {
            if (student.getClassification().equals(classificationType)) {
                System.out.println("  + " + student);
                hasStudent = true;
            }
        }
        if (!hasStudent) {
            System.out.println("  (Không có sinh viên nào thuộc nhóm này)");
        }
    }
}
