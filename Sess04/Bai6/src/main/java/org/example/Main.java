package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số lượng nhân viên (n): ");
        int n = sc.nextInt();
        double[] salaries = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Nhập lương nhân viên thứ " + (i + 1) + ": ");
            salaries[i] = sc.nextDouble();
        }

        while (true) {
            System.out.println("\n--- QUẢN LÝ LƯƠNG NHÂN VIÊN ---");
            System.out.println("1. Xem danh sách lương");
            System.out.println("2. Sắp xếp lương");
            System.out.println("3. Tìm kiếm lương cụ thể");
            System.out.println("4. Thống kê lương");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Danh sách lương hiện tại: " + Arrays.toString(salaries));
                    break;

                case 2:
                    System.out.print("Chọn 1 để tăng dần, 2 để giảm dần: ");
                    int type = sc.nextInt();
                    bubbleSort(salaries, type == 1);
                    System.out.println("Mảng sau khi sắp xếp: " + Arrays.toString(salaries));
                    break;

                case 3:
                    System.out.print("Nhập giá trị lương cần tìm: ");
                    double target = sc.nextDouble();
                    int pos = linearSearch(salaries, target);
                    if (pos != -1) {
                        System.out.println("Tìm thấy lương " + target + " tại vị trí (index): " + pos);
                    } else {
                        System.out.println("Không tìm thấy giá trị lương yêu cầu.");
                    }
                    break;

                case 4:
                    performThongKe(salaries);
                    break;

                case 5:
                    System.out.println("Cảm ơn HoangPK đã sử dụng chương trình!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public static void bubbleSort(double[] arr, boolean ascending) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean condition = ascending ? (arr[j] > arr[j + 1]) : (arr[j] < arr[j + 1]);
                if (condition) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Tìm kiếm tuyến tính (Linear Search)
    public static int linearSearch(double[] arr, double target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // Chức năng thống kê chi tiết
    public static void performThongKe(double[] arr) {
        double total = 0, max = arr[0], min = arr[0];
        for (double s : arr) {
            total += s;
            if (s > max) max = s;
            if (s < min) min = s;
        }
        double average = total / arr.length;
        int aboveAvgCount = 0;
        for (double s : arr) {
            if (s > average) aboveAvgCount++;
        }

        System.out.println("\n--- KẾT QUẢ THỐNG KÊ ---");
        System.out.println("Tổng lương: " + total);
        System.out.println("Lương trung bình: " + average);
        System.out.println("Lương cao nhất: " + max);
        System.out.println("Lương thấp nhất: " + min);
        System.out.println("Số nhân viên có lương trên trung bình: " + aboveAvgCount);
    }
}
