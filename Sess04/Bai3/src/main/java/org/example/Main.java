package org.example;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số lượng phần tử của mảng: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            int temp = arr[maxIdx];
            arr[maxIdx] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Mảng sau khi sắp xếp giảm dần:");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();

        System.out.print("Nhập số cần tìm: ");
        int target = scanner.nextInt();

        int linearRes = linearSearch(arr, target);
        System.out.println("Tìm kiếm tuyến tính: " + (linearRes != -1 ? "Số " + target + " có tại vị trí " + linearRes : "Không tìm thấy"));

        // Tìm kiếm nhị phân (Trên mảng giảm dần)
        int binaryRes = binarySearchDesc(arr, target);
        System.out.println("Tìm kiếm nhị phân: " + (binaryRes != -1 ? "Số " + target + " có tại vị trí " + binaryRes : "Không tìm thấy"));

        scanner.close();
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearchDesc(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;

            // Vì mảng giảm dần nên logic sẽ ngược lại:
            if (arr[mid] < target) {
                right = mid - 1; // Số cần tìm lớn hơn thì phải tìm bên trái
            } else {
                left = mid + 1;  // Số cần tìm nhỏ hơn thì phải tìm bên phải
            }
        }
        return -1;
    }
}
