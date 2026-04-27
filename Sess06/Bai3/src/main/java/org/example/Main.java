package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] danhSachBienSo = new String[100];
        int count = 0;

        // Regex biển số: 2 số - 1 chữ cái - 5 số (có dấu chấm)
        // Ví dụ: 30F-123.45
        String licenseRegex = "^\\d{2}[A-Z]-\\d{3}\\.\\d{2}$";

        while (true) {
            System.out.println("\n*********** QUẢN LÝ BIỂN SỐ XE ***********");
            System.out.println("1. Thêm các biển số xe");
            System.out.println("2. Hiển thị danh sách biển số xe");
            System.out.println("3. Tìm kiếm biển số xe (Chính xác)");
            System.out.println("4. Tìm biển số xe theo mã tỉnh");
            System.out.println("5. Sắp xếp biển số xe tăng dần");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập biển số xe (VD: 30F-123.45): ");
                    String input = sc.nextLine().trim();
                    if (input.matches(licenseRegex)) {
                        danhSachBienSo[count++] = input;
                        System.out.println("Thêm thành công!");
                    } else {
                        System.out.println("Định dạng biển số không hợp lệ!");
                    }
                    break;

                case 2:
                    System.out.println("Danh sách biển số:");
                    for (int i = 0; i < count; i++) {
                        System.out.println("- " + danhSachBienSo[i]);
                    }
                    break;

                case 3:
                    System.out.print("Nhập biển số chính xác cần tìm: ");
                    String target = sc.nextLine();
                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (danhSachBienSo[i].equalsIgnoreCase(target)) {
                            System.out.println("Tìm thấy biển số tại vị trí: " + i);
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Không tìm thấy!");
                    break;

                case 4:
                    System.out.print("Nhập mã tỉnh cần tìm (VD: 16, 29, 30...): ");
                    String provinceCode = sc.nextLine();
                    System.out.println("Kết quả các xe thuộc mã tỉnh " + provinceCode + ":");
                    for (int i = 0; i < count; i++) {
                        // Dùng startsWith để kiểm tra mã tỉnh ở đầu chuỗi
                        if (danhSachBienSo[i].startsWith(provinceCode)) {
                            System.out.println("- " + danhSachBienSo[i]);
                        }
                    }
                    break;

                case 5:
                    if (count > 0) {
                        Arrays.sort(danhSachBienSo, 0, count);
                        System.out.println("Đã sắp xếp danh sách tăng dần.");
                    }
                    break;

                case 6:
                    System.out.println("Tạm biệt HoangPK!");
                    System.exit(0);
            }
        }
    }
}