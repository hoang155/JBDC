package org.example;

import entity.Subject;
import entity.SubjectManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        SubjectManager<Subject> manager = new SubjectManager<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Thêm vài dữ liệu mẫu
        manager.addSubject(new Subject("MH01", "Lap trinh Java", 4, LocalDate.of(2026, 5, 10)));
        manager.addSubject(new Subject("MH02", "Co so du lieu", 3, LocalDate.of(2026, 6, 1)));

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== QUẢN LÝ MÔN HỌC =====");
            System.out.println("1. Hiển thị danh sách môn học");
            System.out.println("2. Thêm môn học");
            System.out.println("3. Xóa môn học theo mã");
            System.out.println("4. Tìm kiếm môn học theo tên");
            System.out.println("5. Lọc môn học theo tín chỉ (> 3)");
            System.out.println("0. Thoát");
            System.out.println("===========================");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Xử lý trôi lệnh

                switch (choice) {
                    case 1:
                        manager.displayAll();
                        break;

                    case 2:
                        System.out.print("Nhập mã môn học (code): ");
                        String code = scanner.nextLine().trim();

                        System.out.print("Nhập tên môn học (name): ");
                        String name = scanner.nextLine().trim();

                        System.out.print("Nhập số tín chỉ (credits): ");
                        int credits = scanner.nextInt();
                        scanner.nextLine();

                        // Kiểm tra điều kiện lỗi nghiệp vụ tín chỉ theo yêu cầu ở ảnh 1
                        if (credits <= 0 || credits > 10) {
                            throw new IllegalArgumentException("Lỗi: Số tín chỉ không hợp lệ! (Phải nằm trong khoảng từ 1 đến 10)");
                        }

                        System.out.print("Nhập ngày bắt đầu (dd-MM-yyyy): ");
                        String dateStr = scanner.nextLine().trim();
                        LocalDate startDate = LocalDate.parse(dateStr, formatter); // Bắt lỗi định dạng ngày tháng

                        Subject newSubject = new Subject(code, name, credits, startDate);
                        manager.addSubject(newSubject);
                        break;

                    case 3:
                        System.out.print("Nhập mã môn học muốn xóa: ");
                        String codeDel = scanner.nextLine().trim();
                        if (manager.deleteByCode(codeDel)) {
                            System.out.println("Đã xóa thành công môn học có mã: " + codeDel);
                        } else {
                            System.out.println("Lỗi: Không tìm thấy môn học tương ứng để xóa!");
                        }
                        break;

                    case 4:
                        System.out.print("Nhập tên môn học cần tìm: ");
                        String searchName = scanner.nextLine().trim();
                        Optional<Subject> result = manager.searchByName(searchName);

                        // Thực hành Optional xử lý trường hợp kết quả rỗng (Ảnh 1)
                        if (result.isPresent()) {
                            System.out.println("Kết quả tìm kiếm: " + result.get());
                        } else {
                            System.out.println("Không có môn học phù hợp");
                        }
                        break;

                    case 5:
                        manager.filterByCredits();
                        break;

                    case 0:
                        System.out.println("Chương trình kết thúc!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số từ 0 đến 5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Lỗi dữ liệu: Bạn phải nhập vào một giá trị số hợp lệ!");
                scanner.nextLine(); // Clear bộ đệm
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // Hiển thị lỗi biên số tín chỉ (âm hoặc > 10)
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi định dạng: Ngày tháng nhập vào không hợp lệ (Định dạng chuẩn: dd-MM-yyyy)!");
            }
        }
        scanner.close();
    }
}
