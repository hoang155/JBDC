package entity;

public class BankAccount {
    // Thuộc tính
    private String accountId;
    private double balance;

    // Constructor rỗng
    public BankAccount() {
    }

    // Constructor đầy đủ tham số
    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // Getter và Setter
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Chức năng gửi tiền (Deposit)
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Lỗi: Số tiền gửi phải lớn hơn 0!");
        }
        this.balance += amount;
        System.out.printf("Gửi thành công %.2f vào tài khoản %s.\n", amount, this.accountId);
    }

    /**
     * Chức năng rút tiền (Withdraw)
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Lỗi: Số tiền rút phải lớn hơn 0!");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Lỗi: Số tiền rút vượt quá số dư hiện có!");
        }
        this.balance -= amount;
        System.out.printf("Rút thành công %.2f từ tài khoản %s.\n", amount, this.accountId);
    }
}