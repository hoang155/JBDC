package org.example;

public abstract class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public Payment() {
    }

    public abstract void pay();

    public void printAmount() {
        System.out.println("Số tiền thanh toán: " + amount + " VND");
    }

    public double getAmount() {
        return amount;
    }
}
