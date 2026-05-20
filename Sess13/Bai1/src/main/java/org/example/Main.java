package org.example;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static final LinkedList<Person> userList = new LinkedList<>();

    static void main() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******** MENU QUẢN LÍ NGƯỜI DÙNG ********");
            System.out.println("1. Thêm người dùng");
            System.out.println("2. Xóa người dùng");
            System.out.println("3. Hiển thị danh sách người dùng");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn : ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addPerson(scanner);
                    break;
                case 2:
                    deletePerson(scanner);
                    break;
                case 3:
                    displayUsers();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }

    private static void deletePerson(Scanner scanner) {
        if (userList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh sách trống, không có dữ liệu để xóa.");
            return;
        }

        System.out.print("Nhập email của người dùng bạn muốn xóa khỏi hệ thống: ");
        String searchEmail = scanner.nextLine().trim();

        int foundIndex = -1;
        // Duyệt danh sách tìm vị trí phần tử chứa email trùng khớp
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equalsIgnoreCase(searchEmail)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {
            System.out.println("Lỗi: Không tìm thấy người dùng nào ứng với email: " + searchEmail);
        } else {
            // Xóa phần tử khỏi LinkedList tại vị trí tìm thấy
            Person removedPerson = userList.remove(foundIndex);
            System.out.println("Đã xóa thành công người dùng: " + removedPerson.getName());
        }
    }

    private static void addPerson(Scanner scanner) {
        Person newPerson = new Person();
        newPerson.inputData(scanner);

        userList.add(newPerson); // Thêm phần tử vào LinkedList
        System.out.println("Người dùng đã được thêm thành công.");
    }

    private static void displayUsers() {
        if (userList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Hiện tại chưa có người dùng nào trong hệ thống.");
            return;
        }

        System.out.println("\n--- DANH SÁCH NGƯỜI DÙNG HIỆN TẠI ---");
        for (Person p : userList) {
            p.displayData();
        }
        System.out.println("-------------------------------------");
    }
}

