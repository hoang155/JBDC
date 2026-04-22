package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        // 1. Nhập số phần tử
        System.out.print("Nhập số phần tử của mảng: ");
        int n = scanner.nextInt();

        // 2. Kiểm tra mảng hợp lệ (TC3)
        if (n <= 0) {
            System.out.println("Mảng không có phần tử");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Nhập giá trị các phần tử:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // 3. Xử lý phân loại chẵn trước, lẻ sau và giữ nguyên thứ tự
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();

        for (int num : arr) {
            if (num % 2 == 0) {
                evens.add(num); // Thêm vào nhóm chẵn
            } else {
                odds.add(num);  // Thêm vào nhóm lẻ
            }
        }

        // Gộp nhóm chẵn và nhóm lẻ vào mảng kết quả
        int[] result = new int[n];
        int index = 0;

        for (int num : evens) {
            result[index++] = num;
        }
        for (int num : odds) {
            result[index++] = num;
        }

        // 4. In kết quả (TC1, TC2)
        System.out.print("Mảng sau khi sắp xếp: ");
        for (int i = 0; i < n; i++) {
            System.out.print(result[i] + (i == n - 1 ? "" : " "));
        }

        scanner.close();
    }
}
