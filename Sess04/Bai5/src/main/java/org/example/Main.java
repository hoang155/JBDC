package org.example;

import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số lượng sinh viên (n): ");
        int n = sc.nextInt();
        double[] diem = new double[n];
        boolean isSorted = false;

        for (int i = 0; i < n; i++) {
            System.out.print("Nhập điểm sinh viên thứ " + (i + 1) + ": ");
            diem[i] = sc.nextDouble();
        }

        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐIỂM SINH VIÊN ---");
            System.out.println("1. Xem tất cả điểm");
            System.out.println("2. Sắp xếp điểm");
            System.out.println("3. Tìm kiếm điểm");
            System.out.println("4. Thống kê điểm");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Danh sách điểm: " + Arrays.toString(diem));
                    break;

                case 2:
                    System.out.println("1. Tăng dần | 2. Giảm dần");
                    int sortType = sc.nextInt();
                    bubbleSort(diem, sortType == 1);
                    isSorted = true;
                    System.out.println("Mảng sau sắp xếp: " + Arrays.toString(diem));
                    break;

                case 3:
                    System.out.print("Nhập điểm cần tìm: ");
                    double target = sc.nextDouble();
                    // Tìm kiếm tuyến tính
                    int linearPos = linearSearch(diem, target);
                    System.out.println("(Linear Search) Vị trí: " + (linearPos != -1 ? linearPos : "Không tìm thấy"));

                    // Tìm kiếm nhị phân (Yêu cầu mảng đã sắp xếp)
                    if (isSorted) {
                        int binaryPos = binarySearch(diem, target);
                        System.out.println("(Binary Search) Vị trí: " + (binaryPos >= 0 ? binaryPos : "Không tìm thấy"));
                    } else {
                        System.out.println("Lưu ý: Mảng chưa sắp xếp, không thể dùng Binary Search chính xác.");
                    }
                    break;

                case 4:
                    thongKe(diem);
                    break;

                case 5:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
            }
        }
    }

    // Thuật toán Bubble Sort
    public static void bubbleSort(double[] arr, boolean ascending) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ascending ? (arr[j] > arr[j + 1]) : (arr[j] < arr[j + 1])) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Tìm kiếm tuyến tính
    public static int linearSearch(double[] arr, double target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // Tìm kiếm nhị phân (Sử dụng thư viện Arrays để tối ưu cho bài tập lớn)
    public static int binarySearch(double[] arr, double target) {
        return Arrays.binarySearch(arr, target);
    }

    // Thống kê điểm
    public static void thongKe(double[] arr) {
        double sum = 0, max = arr[0], min = arr[0];
        int countAboveAvg = 0;
        for (double d : arr) {
            sum += d;
            if (d > max) max = d;
            if (d < min) min = d;
        }
        double avg = sum / arr.length;
        for (double d : arr) if (d >= 5.0) countAboveAvg++; // Giả định trên trung bình là >= 5.0

        System.out.println("--- THỐNG KÊ ---");
        System.out.println("Điểm trung bình: " + avg);
        System.out.println("Cao nhất: " + max + " | Thấp nhất: " + min);
        System.out.println("Số SV đạt điểm trên trung bình: " + countAboveAvg);
    }
}

