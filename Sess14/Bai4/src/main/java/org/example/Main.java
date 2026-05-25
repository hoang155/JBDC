package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import CustomException.InvalidPhoneNumberLengthException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        // Danh sách lưu các số điện thoại hợp lệ
        ArrayList<String> dsHopLe = new ArrayList<>();
        // Danh sách lưu các số điện thoại KHÔNG hợp lệ (kèm lý do lỗi)
        ArrayList<String> dsKhongHopLe = new ArrayList<>();

        System.out.println("Nhập chuỗi các số điện thoại (ngăn cách bằng dấu phẩy):");
        String input = scanner.nextLine();

        // Tách chuỗi đầu vào thành mảng các số điện thoại dựa vào dấu phẩy
        String[] phoneNumbers = input.split(",");

        // Duyệt qua từng số điện thoại trong mảng
        for (String phone : phoneNumbers) {
            // Loại bỏ khoảng trắng thừa ở hai đầu nếu người dùng gõ kiểu ", "
            String trimmedPhone = phone.trim();

            // Nếu chuỗi rỗng do nhập lỗi dạng "0912345678,,0987654321" thì bỏ qua
            if (trimmedPhone.isEmpty()) {
                continue;
            }

            try {
                // Gọi phương thức kiểm tra tính hợp lệ
                validatePhoneNumber(trimmedPhone);
                // Nếu không có ngoại lệ nào bị ném ra -> Số điện thoại hợp lệ
                dsHopLe.add(trimmedPhone);
            } catch (InvalidPhoneNumberLengthException e) {
                // Nếu bắt được ngoại lệ -> Lưu số kèm theo lý do lỗi (chính là message của Exception)
                dsKhongHopLe.add(trimmedPhone + " : " + e.getMessage());
            }
        }

        // --- HIỂN THỊ KẾT QUẢ ĐẦU RA ---
        System.out.println("\nSố điện thoại hợp lệ:");
        if (dsHopLe.isEmpty()) {
            System.out.println(" - (Không có)");
        } else {
            for (String p : dsHopLe) {
                System.out.println(" - " + p);
            }
        }

        System.out.println("\nSố điện thoại không hợp lệ:");
        if (dsKhongHopLe.isEmpty()) {
            System.out.println(" - (Không có)");
        } else {
            for (String errorLog : dsKhongHopLe) {
                System.out.println(" - " + errorLog);
            }
        }

        scanner.close();
    }

    /**
     * Phương thức kiểm tra tính hợp lệ của số điện thoại.
     * Ném ra ngoại lệ tự định nghĩa nếu vi phạm bất kỳ quy tắc nào.
     */
    public static void validatePhoneNumber(String phone) throws InvalidPhoneNumberLengthException {
        // Quy tắc 1: Không được chứa khoảng trắng ở GIỮA chuỗi
        // (Do lệnh trim() ở trên chỉ xóa khoảng trắng 2 đầu, nếu ở giữa có space thì vẫn còn)
        if (phone.contains(" ")) {
            throw new InvalidPhoneNumberLengthException("Không được chứa khoảng trắng");
        }

        // Quy tắc 2: Phải gồm ĐÚNG 10 chữ số
        if (phone.length() != 10) {
            throw new InvalidPhoneNumberLengthException("Sai độ dài (phải chứa đúng 10 ký tự)");
        }

        // Quy tắc 3: Chỉ chứa các ký tự số (0-9), không chứa chữ hay ký tự đặc biệt
        // Sử dụng biểu thức chính quy (Regex): \\d đại diện cho chữ số từ 0-9
        // Nếu chuỗi không khớp hoàn toàn với 10 chữ số -> Có chứa ký tự lạ
        if (!phone.matches("\\d{10}")) {
            throw new InvalidPhoneNumberLengthException("Chứa ký tự không hợp lệ");
        }
    }
}
