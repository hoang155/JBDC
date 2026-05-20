package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import entity.Contact;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    // Sử dụng HashSet để tự động hóa việc kiểm tra trùng lặp qua equals() và hashCode()
    private static final HashSet<Contact> contactBook = new HashSet<>();
    private static int autoIncrementId = 1; // ID tự động tăng

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n============== MENU ==============");
            System.out.println("1. Thêm liên lạc");
            System.out.println("2. Xóa liên lạc theo số điện thoại");
            System.out.println("3. Tìm kiếm liên lạc theo số điện thoại");
            System.out.println("4. Hiển thị danh bạ");
            System.out.println("0. Thoát");
            System.out.println("==================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact(scanner);
                    break;
                case "2":
                    deleteContactByPhone(scanner);
                    break;
                case "3":
                    searchContactByPhone(scanner);
                    break;
                case "4":
                    displayContactBook();
                    break;
                case "0":
                    System.out.println("👋 Đang đóng chương trình danh bạ. Tạm biệt!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ! Vui lòng nhập từ 0 đến 4.");
            }
        }
    }

    // Chức năng 1: Thêm liên lạc mới (Có bẫy trùng lặp theo yêu cầu)
    private static void addContact(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Nhập tên người liên lạc: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("⚠️ Lỗi: Tên liên lạc không được để trống!");
                continue;
            }
            break;
        }

        String phone;
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("⚠️ Lỗi: Số điện thoại không được để trống!");
                continue;
            }
            if (!phone.matches("^\\d+$")) {
                System.out.println("❌ Lỗi: Số điện thoại chỉ được chứa các ký tự số!");
                continue;
            }
            break;
        }

        // Tạo một đối tượng tạm thời để kiểm tra cơ chế chứa (contains) của Set
        Contact newContact = new Contact(autoIncrementId, name, phone);

        // Sử dụng cơ chế contains() - hàm này sẽ gọi hashCode() và equals() ngầm bên dưới
        if (contactBook.contains(newContact)) {
            System.out.println("❌ Thông báo: Số điện thoại đã tồn tại");
        } else {
            contactBook.add(newContact);
            autoIncrementId++; // Chỉ tăng ID hệ thống khi thêm mới thành công
            System.out.println("✅ Thêm liên lạc mới thành công.");
        }
    }

    // Chức năng 2: Xóa liên lạc theo số điện thoại ứng dụng phương thức remove()
    private static void deleteContactByPhone(Scanner scanner) {
        if (contactBook.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh bạ hiện tại đang trống.");
            return;
        }

        System.out.print("Nhập số điện thoại cần xóa: ");
        String phone = scanner.nextLine().trim();

        // Tạo đối tượng giả lập mang số điện thoại cần tìm để Set thực hiện đối chiếu cấu trúc
        Contact target = new Contact(0, "", phone);

        if (contactBook.remove(target)) {
            System.out.println("✅ Xóa thành công");
        } else {
            System.out.println("⚠️ Không tìm thấy liên lạc");
        }
    }

    // Chức năng 3: Tìm kiếm liên lạc dùng chứa kiểm tra contains()
    private static void searchContactByPhone(Scanner scanner) {
        if (contactBook.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh bạ trống, không có dữ liệu tìm kiếm.");
            return;
        }

        System.out.print("Nhập số điện thoại cần tìm kiếm: ");
        String phone = scanner.nextLine().trim();

        Contact target = new Contact(0, "", phone);

        if (contactBook.contains(target)) {
            System.out.println("✅ Có tồn tại liên lạc:");
            // Lấy ra thông tin chi tiết chính xác của đối tượng nằm trong Set
            for (Contact c : contactBook) {
                if (c.equals(target)) {
                    System.out.println("   -> " + c);
                    break;
                }
            }
        } else {
            System.out.println("⚠️ Không tồn tại");
        }
    }

    // Chức năng 4: In toàn bộ danh bạ hiện có
    private static void displayContactBook() {
        if (contactBook.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh bạ hiện tại đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH LIÊN LẠC TRONG DANH BẠ ---");
        for (Contact c : contactBook) {
            System.out.println(c);
        }
        System.out.printf("📊 Tổng cộng: %d liên lạc.\n", contactBook.size());
    }
}