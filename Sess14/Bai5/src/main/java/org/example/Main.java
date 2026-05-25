package org.example;

import entity.BankAccount;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static List<BankAccount> accountList = new ArrayList<>();
    static void main() {
// 1. Thêm một số tài khoản mẫu vào danh sách
        accountList.add(new BankAccount("TK001", 500000));
        accountList.add(new BankAccount("TK002", 1000000));

        System.out.println("=== TRẠNG THÁI BAN ĐẦU ===");
        printAllAccounts();

        // 2. Kiểm thử chức năng Gửi tiền (Deposit)
        System.out.println("\n--- Thực hiện Gửi tiền ---");
        try {
            BankAccount acc1 = findAccount("TK001");
            if (acc1 != null) {
                acc1.deposit(200000); // Gửi hợp lệ
                acc1.deposit(-50000);  // Thử gửi số âm để kích hoạt lỗi
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // 3. Kiểm thử chức năng Rút tiền (Withdraw)
        System.out.println("\n--- Thực hiện Rút tiền ---");
        try {
            BankAccount acc2 = findAccount("TK002");
            if (acc2 != null) {
                acc2.withdraw(300000);  // Rút hợp lệ
                acc2.withdraw(2000000); // Thử rút quá số dư để kích hoạt lỗi
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // 4. Kiểm thử chức năng Chuyển tiền (Transfer)
        System.out.println("\n--- Thực hiện Chuyển tiền ---");
        // Kịch bản 4.1: Chuyển tiền thành công
        try {
            transfer("TK002", "TK001", 400000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Kịch bản 4.2: Thử chuyển tiền tới tài khoản không tồn tại
        try {
            transfer("TK001", "TK999", 50000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // 5. In kết quả cuối cùng để kiểm tra hoạt động
        System.out.println("\n=== TRẠNG THÁI CUỐI CÙNG ===");
        printAllAccounts();
    }

    /**
     * Chức năng chuyển tiền giữa hai tài khoản (Transfer)
     */
    public static void transfer(String sourceId, String destId, double amount) {
        BankAccount sourceAcc = findAccount(sourceId);
        BankAccount destAcc = findAccount(destId);

        // Kiểm tra tài khoản đích có tồn tại trong hệ thống hay không
        if (destAcc == null) {
            throw new IllegalArgumentException("Lỗi: Tài khoản đích (" + destId + ") không tồn tại trên hệ thống!");
        }
        if (sourceAcc == null) {
            throw new IllegalArgumentException("Lỗi: Tài khoản nguồn (" + sourceId + ") không tồn tại!");
        }

        // Tận dụng lại logic kiểm tra số tiền và số dư từ hàm withdraw/deposit
        // Nếu rút từ tài khoản nguồn bị lỗi, lệnh ném lỗi sẽ dừng hàm ngay lập tức
        sourceAcc.withdraw(amount);
        destAcc.deposit(amount);

        System.out.printf("Chuyển khoản thành công %.2f từ %s sang %s.\n", amount, sourceId, destId);
    }

    /**
     * Hàm trợ giúp tìm kiếm tài khoản trong List dựa trên accountId
     */
    private static BankAccount findAccount(String accountId) {
        for (BankAccount acc : accountList) {
            if (acc.getAccountId().equalsIgnoreCase(accountId)) {
                return acc;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    /**
     * Hàm in danh sách tài khoản
     */
    private static void printAllAccounts() {
        for (BankAccount acc : accountList) {
            System.out.printf("Tài khoản: %s | Số dư: %,.2fđ\n", acc.getAccountId(), acc.getBalance());
        }
    }
}
