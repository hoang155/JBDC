package org.example;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,###.00");

        System.out.println("========== NHẬP THÔNG TIN HÓA ĐƠN ==========");

        System.out.print("Nhập tên khách hàng: ");
        String customerName = scanner.nextLine();

        System.out.print("Nhập tên sản phẩm: ");
        String productName = scanner.nextLine();

        System.out.print("Nhập giá sản phẩm: ");
        double price = scanner.nextDouble();

        System.out.print("Nhập số lượng mua: ");
        int quantity = scanner.nextInt();

        System.out.print("Khách có thẻ thành viên? (true/false): ");
        boolean isMember = scanner.nextBoolean();

        // --- XỬ LÝ TÍNH TOÁN ---
        double totalAmount = price * quantity;

        // Giảm giá 10% nếu là thành viên
        double discount = isMember ? (totalAmount * 0.1) : 0;

        // Thuế VAT 8% (Tính trên tổng tiền sau khi đã biết giá trị hàng)
        double vat = totalAmount * 0.08;

        // Tổng thanh toán cuối cùng
        double finalPayment = totalAmount - discount + vat;

        // --- IN HÓA ĐƠN ---
        System.out.println("\n==================== HÓA ĐƠN ====================");
        System.out.println("Khách hàng: " + customerName);
        System.out.println("Sản phẩm  : " + productName);
        System.out.println("Số lượng  : " + quantity);
        System.out.println("Đơn giá   : " + df.format(price) + " VND");
        System.out.println("Thành tiền: " + df.format(totalAmount) + " VND");
        System.out.println("Giảm giá thành viên (10%): " + df.format(discount) + " VND");
        System.out.println("Tiền VAT (8%): " + df.format(vat) + " VND");
        System.out.println("Tổng thanh toán: " + df.format(finalPayment) + " VND");
        System.out.println("=================================================");

        scanner.close();
    }
}
