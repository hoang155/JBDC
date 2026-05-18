package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final ArrayList<Staff> staffList = new ArrayList<>();
    private static int autoIncrementId = 1; // Tự động tăng ID để không bị trùng lặp

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n================ QUẢN LÝ NHÂN SỰ TRUNG TÂM ================");
            System.out.println("1. Thêm mới nhân viên (Giảng viên / Hành chính)");
            System.out.println("2. Hiển thị danh sách nhân viên & Tính lương");
            System.out.println("3. Cập nhật thông tin nhân viên theo ID");
            System.out.println("4. Xóa nhân viên khỏi hệ thống theo ID");
            System.out.println("5. Thoát");
            System.out.println("===========================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addNewStaff(scanner);
                    break;
                case "2":
                    displayAllStaff();
                    break;
                case "3":
                    updateStaffById(scanner);
                    break;
                case "4":
                    deleteStaffById(scanner);
                    break;
                case "5":
                    System.out.println("👋 Hệ thống đóng. Tạm biệt!");
                    System.exit(0);
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ! Vui lòng chọn từ 1 đến 5.");
            }
        }
    }

    // Chức năng 1: Thêm mới nhân viên
    private static void addNewStaff(Scanner scanner) {
        System.out.println("\nChọn loại nhân viên muốn thêm mới:");
        System.out.println("1. Giảng viên (Lecturer)");
        System.out.println("2. Nhân viên hành chính (AdminStaff)");
        System.out.print("Lựa chọn: ");
        String type = scanner.nextLine().trim();

        Staff newStaff = null;
        if (type.equals("1")) {
            newStaff = new Lecturer();
        } else if (type.equals("2")) {
            newStaff = new AdminStaff();
        } else {
            System.out.println("❌ Lỗi: Loại nhân viên không hợp lệ!");
            return;
        }

        // Tạo id tự động tăng và nhập data
        newStaff.setId(autoIncrementId++);
        newStaff.inputData(scanner);

        // Thêm vào danh sách ArrayList
        staffList.add(newStaff);
        System.out.println("✅ Thêm nhân viên mới vào hệ thống thành công!");
    }

    // Chức năng 2: Hiển thị và áp dụng tính đa hình
    private static void displayAllStaff() {
        if (staffList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh sách nhân sự đang trống.");
            return;
        }

        System.out.println("\n=================== DANH SÁCH NHÂN SỰ CHINH XÁC ===================");
        for (Staff st : staffList) {
            // Gọi phương thức displayData() Đa hình (Tự biết là Giảng viên hay Admin để in)
            st.displayData();

            // Nếu đối tượng có liên kết interface đặc thù -> Check đánh giá hiệu suất
            if (st instanceof ICapability) {
                ((ICapability) st).checkPerformance();
            }
        }
        System.out.println("====================================================================");
    }

    // Chức năng 3: Cập nhật thông tin nhân viên theo ID
    private static void updateStaffById(Scanner scanner) {
        System.out.print("Nhập ID nhân viên cần cập nhật thông tin: ");
        try {
            int searchId = Integer.parseInt(scanner.nextLine().trim());
            Staff foundStaff = null;

            for (Staff st : staffList) {
                if (st.getId() == searchId) {
                    foundStaff = st;
                    break;
                }
            }

            if (foundStaff == null) {
                System.out.println("❌ Lỗi: Không tìm thấy nhân viên nào có ID = " + searchId);
                return;
            }

            System.out.println("--- Tiến hành nhập lại thông tin mới cho ID: " + searchId + " ---");
            foundStaff.inputData(scanner);
            System.out.println("✅ Cập nhật dữ liệu nhân sự thành công!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: ID nhập vào bắt buộc phải là số nguyên!");
        }
    }

    // Chức năng 4: Xóa nhân viên theo ID
    private static void deleteStaffById(Scanner scanner) {
        System.out.print("Nhập ID nhân viên muốn xóa: ");
        try {
            int searchId = Integer.parseInt(scanner.nextLine().trim());
            int targetIndex = -1;

            for (int i = 0; i < staffList.size(); i++) {
                if (staffList.get(i).getId() == searchId) {
                    targetIndex = i;
                    break;
                }
            }

            if (targetIndex == -1) {
                System.out.println("❌ Lỗi: Không tồn tại nhân viên có ID trùng khớp để xóa!");
            } else {
                Staff removed = staffList.remove(targetIndex); // Xóa khỏi danh sách cực kỳ ngắn gọn
                System.out.println("✅ Đã xóa thành công nhân viên: " + removed.getName() + " [ID: " + searchId + "]");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: ID phải là số nguyên hợp lệ!");
        }
    }
}
