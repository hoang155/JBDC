package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
// Khởi tạo các biến định mức theo yêu cầu đề bài
        long soDuBanDau = 1000000; // 1.000.000 đồng
        long soDuToiThieu = 50000;  // 50.000 đồng

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- HỆ THỐNG ATM MÔ PHỎNG ---");
        System.out.printf("Số dư hiện tại của bạn: %,d đồng\n", soDuBanDau);
        System.out.print("Nhập số tiền bạn muốn rút: ");

        try {
            // Đọc số tiền cần rút từ bàn phím
            long soTienRut = scanner.nextLong();

            // Kiểm tra điều kiện nghiệp vụ 1: Nhập số tiền âm hoặc bằng 0
            if (soTienRut <= 0) {
                System.out.println("Lỗi: Số tiền rút phải lớn hơn 0!");
            }
            // Kiểm tra điều kiện nghiệp vụ 2: Số tiền rút lớn hơn số dư hiện có
            else if (soTienRut > soDuBanDau) {
                System.out.println("Lỗi: Số tiền rút vượt quá số dư!");
            }
            // Kiểm tra điều kiện nghiệp vụ 3: Số dư còn lại sau khi rút nhỏ hơn 50.000đ
            else if (soDuBanDau - soTienRut < soDuToiThieu) {
                System.out.println("Lỗi: Tài khoản phải duy trì số dư tối thiểu 50.000 đồng!");
            }
            // Thỏa mãn tất cả các điều kiện nghiệp vụ -> Rút tiền thành công
            else {
                soDuBanDau -= soTienRut; // Thực hiện trừ tiền
                System.out.println("\n>> GIAO DỊCH THÀNH CÔNG <<");
                System.out.printf("Số tiền đã rút: %,d đồng\n", soTienRut);
                System.out.printf("Số dư còn lại trong tài khoản: %,d đồng\n", soDuBanDau);
            }

        } catch (InputMismatchException e) {
            // Xử lý ngoại lệ khi người dùng nhập chữ, ký tự đặc biệt thay vì nhập số
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
        } finally {
            // Luôn đóng scanner khi kết thúc để tránh rò rỉ bộ nhớ
            scanner.close();
        }
    }
}
