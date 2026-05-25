    package org.example;

    import java.util.ArrayList;
    import java.util.Scanner;

    //TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        // Khởi tạo ArrayList để lưu danh sách các số nguyên hợp lệ
        ArrayList<Integer> danhSachHopLe = new ArrayList<>();

        // Biến đếm số chuỗi không hợp lệ
        int soChuoiKhongHopLe = 0;

        System.out.println("Nhập các chuỗi (Nhập 'exit' để kết thúc):");

        // Vòng lặp cho phép người dùng nhập liên tục cho đến khi gõ 'exit'
        while (true) {
            System.out.print("Nhập chuỗi: ");
            String input = scanner.nextLine().trim();

            // Kiểm tra ký hiệu kết thúc chương trình
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            // Tiến hành cố gắng chuyển đổi chuỗi sang số nguyên
            try {
                int soNguyen = Integer.parseInt(input);
                // Nếu chuyển đổi thành công -> lưu vào danh sách hợp lệ
                danhSachHopLe.add(soNguyen);
            } catch (NumberFormatException e) {
                // Nếu chuyển đổi thất bại (chuỗi chứa chữ, ký tự đặc biệt, số thực) -> bắt ngoại lệ
                soChuoiKhongHopLe++;
            }
        }

        // --- Thống kê và hiển thị kết quả ra màn hình ---
        System.out.println("\n--- KẾT QUẢ THỐNG KÊ ---");
        System.out.println("Số chuỗi hợp lệ: " + danhSachHopLe.size());
        System.out.println("Số chuỗi không hợp lệ: " + soChuoiKhongHopLe);
        System.out.println("Danh sách số nguyên hợp lệ: " + danhSachHopLe);

        // Đóng tài nguyên
        scanner.close();
    }
}
