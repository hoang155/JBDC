package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String hoTen = "", email = "", sdt = "", matKhau = "";

        while (true) {
            System.out.println("\n*********** QUẢN LÝ NGƯỜI DÙNG ***********");
            System.out.println("1. Nhập thông tin người dùng");
            System.out.println("2. Chuẩn hóa họ tên");
            System.out.println("3. Kiểm tra email hợp lệ");
            System.out.println("4. Kiểm tra số điện thoại hợp lệ");
            System.out.println("5. Kiểm tra mật khẩu hợp lệ");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập họ và tên: ");
                    hoTen = sc.nextLine();
                    System.out.print("Nhập email: ");
                    email = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    sdt = sc.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    matKhau = sc.nextLine();
                    break;

                case 2:
                    hoTen = chuanHoaHoTen(hoTen);
                    System.out.println("Họ tên sau chuẩn hóa: " + hoTen);
                    break;

                case 3:
                    // Regex email cơ bản
                    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
                    System.out.println(email.matches(emailRegex) ? "Email hợp lệ" : "Email không hợp lệ");
                    break;

                case 4:
                    // Regex SĐT di động Việt Nam (10 số, bắt đầu bằng 03, 05, 07, 08, 09)
                    String sdtRegex = "^(03|05|07|08|09|01[2|6|8|9])\\d{8}$";
                    System.out.println(sdt.matches(sdtRegex) ? "Số điện thoại hợp lệ" : "Số điện thoại không hợp lệ");
                    break;

                case 5:
                    // Regex mật khẩu: >=8 ký tự, hoa, thường, số, đặc biệt
                    String mkRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
                    System.out.println(matKhau.matches(mkRegex) ? "Mật khẩu hợp lệ" : "Mật khẩu không hợp lệ");
                    break;

                case 6:
                    System.out.println("Tạm biệt!");
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn sai!");
            }
        }
    }

    // Hàm chuẩn hóa họ tên: Xóa khoảng trắng thừa, Viết hoa chữ cái đầu mỗi từ
    public static String chuanHoaHoTen(String str) {
        if (str == null || str.isEmpty()) return "";
        str = str.trim().replaceAll("\\s+", " ").toLowerCase();
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0)))
                    .append(w.substring(1))
                    .append(" ");
        }
        return sb.toString().trim();
    }
}