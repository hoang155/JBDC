package org.example;

public class CashPayment extends Payment{
    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.print("[Thẻ tín dụng] ");
        printAmount();
    }
}
