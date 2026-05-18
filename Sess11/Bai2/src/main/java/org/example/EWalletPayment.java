package org.example;

public class EWalletPayment extends Payment implements Refundable {
    public EWalletPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.print("[Ví điện tử] ");
        printAmount();
    }

    @Override
    public void refund() {
        System.out.println("-> Đang hoàn lại " + getAmount() + " VND vào tài khoản Ví điện tử.");
    }
}
