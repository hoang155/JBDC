package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        int iterations = 100000;
        String content = " World";

        long startTime = System.currentTimeMillis();
        String str = "Hello";

        for (int i = 0; i < 10000; i++) {
            str += content;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Thời gian thực hiện với String: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("Hello");
        for (int i = 0; i < iterations; i++) {
            sb.append(content);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Thời gian thực hiện với StringBuilder: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer("Hello");
        for (int i = 0; i < iterations; i++) {
            sbf.append(content);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Thời gian thực hiện với StringBuffer: " + (endTime - startTime) + " ms");

        System.out.println("\n--- Nhận xét ---");
        System.out.println("- String: Không hiệu quả cho phép nối chuỗi nhiều lần do tạo ra nhiều đối tượng mới");
        System.out.println("- StringBuilder: Hiệu quả và nhanh chóng, thích hợp cho nhiều thao tác nối chuỗi trong một luồng");
        System.out.println("- StringBuffer: Tương tự StringBuilder nhưng an toàn với đa luồng, có thể chậm hơn do đồng bộ hóa");
    }
}
