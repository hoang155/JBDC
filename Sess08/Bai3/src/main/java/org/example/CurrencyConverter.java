package org.example;

import java.text.DecimalFormat;

public class CurrencyConverter {
    private static double exchangeRate = 25000.0;

    // Phương thức static đặt tỉ giá mới với validation
    public static void setRate(double r) {
        if (r > 0) {
            exchangeRate = r;
            System.out.println("Cập nhật tỉ giá thành công: 1 USD = " + r + " VND");
        } else {
            System.out.println("Lỗi: Tỉ giá phải lớn hơn 0!");
        }
    }

    // Phương thức static lấy tỉ giá hiện tại
    public static double getRate() {
        return exchangeRate;
    }

    // Chuyển đổi VND sang USD (Sử dụng long để tránh tràn số nếu số tiền lớn)
    public static double toUSD(long vnd) {
        if (vnd < 0) {
            System.out.println("Lỗi: Số tiền không được âm!");
            return 0;
        }
        return (double) vnd / exchangeRate;
    }

    // Định dạng hiển thị USD (Làm tròn và thêm dấu phân cách)
    public static String formatUSD(double usd) {
        // Định dạng lấy 2 chữ số thập phân, phân cách hàng nghìn bằng dấu phẩy
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return df.format(usd);
    }
}
