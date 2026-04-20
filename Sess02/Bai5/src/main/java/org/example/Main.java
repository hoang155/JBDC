package org.example;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập vào một số (100-999): ");
        int n = scanner.nextInt();

        if (n < 100 || n > 999) {
            System.out.println("Số nhập vào không hợp lệ");
        } else {
            int hundreds = n / 100;
            int tens = (n % 100) / 10;
            int units = n % 10;

            String result = "";

            result += readDigit(hundreds) + " trăm";

            if (tens == 0 && units != 0) {
                result += " lẻ";
            } else if (tens == 1) {
                result += " mười";
            } else if (tens > 1) {
                result += " " + readDigit(tens) + " mươi";
            }

            if (units != 0) {
                if (units == 5 && tens >= 1) {
                    result += " lăm";
                } else if (units == 1 && tens > 1) {
                    result += " mốt";
                } else {
                    result += " " + readDigit(units).toLowerCase();
                }
            }
            System.out.println(result.substring(0, 1).toUpperCase() + result.substring(1));
        }
        scanner.close();
    }

    public static String readDigit(int digit) {
        switch (digit) {
            case 1: return "Một";
            case 2: return "Hai";
            case 3: return "Ba";
            case 4: return "Bốn";
            case 5: return "Năm";
            case 6: return "Sáu";
            case 7: return "Bảy";
            case 8: return "Tám";
            case 9: return "Chín";
            default: return "";
        }
    }
}
