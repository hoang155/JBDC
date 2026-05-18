package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Payment[] payments = new Payment[3];
        payments[0] = new CashPayment(150000);
        payments[1] = new CreditCardPayment(2500000);
        payments[2] = new EWalletPayment(50000);

        System.out.println("--- XỬ LÝ HỆ THỐNG THANH TOÁN ---");

        for (Payment p : payments) {
            p.pay();

            if (p instanceof Refundable) {
                Refundable refundableObj = (Refundable) p;
                refundableObj.refund();
            }
            System.out.println("---------------------------------");
        }
    }
}
