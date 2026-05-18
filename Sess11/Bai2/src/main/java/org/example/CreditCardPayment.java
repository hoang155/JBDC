package org.example;

public class CreditCardPayment extends Payment implements Refundable {
    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.print("[Thẻ tín dụng] ");
        printAmount();
    }

    @Override
    public void refund() {
        System.out.println("-> Đang hoàn lại " + getAmount() + " VND vào tài khoản Thẻ tín dụng.");
    }
}
