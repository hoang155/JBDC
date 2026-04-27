package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] diemArr = null;
        int n = 0;

        while (true) {
            System.out.println("\n*********** QUẢN LÝ ĐIỂM SV ***********");
            System.out.println("1. Nhập danh sách điểm sinh viên");
            System.out.println("2. In danh sách điểm");
            System.out.println("3. Tính điểm trung bình");
            System.out.println("4. Tìm điểm cao nhất và thấp nhất");
            System.out.println("5. Đếm số lượng sinh viên đạt và trượt");
            System.out.println("6. Sắp xếp điểm tăng dần");
            System.out.println("7. Thống kê số lượng sinh viên giỏi và xuất sắc");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng sinh viên: ");
                    n = Integer.parseInt(sc.nextLine());
                    diemArr = new double[n];
                    for (int i = 0; i < n; i++) {
                        System.out.print("Nhập điểm cho sinh viên thứ " + (i + 1) + ": ");
                        diemArr[i] = Double.parseDouble(sc.nextLine());
                    }
                    break;

                case 2:
                    if (diemArr == null) {
                        System.out.println("Dữ liệu trống!");
                    } else {
                        System.out.println("Danh sách điểm: " + Arrays.toString(diemArr));
                    }
                    break;

                case 3:
                    if (diemArr != null) {
                        double sum = 0;
                        for (double d : diemArr) sum += d;
                        System.out.printf("Điểm trung bình của lớp: %.2f\n", (sum / n));
                    }
                    break;

                case 4:
                    if (diemArr != null) {
                        double max = diemArr[0], min = diemArr[0];
                        for (double d : diemArr) {
                            if (d > max) max = d;
                            if (d < min) min = d;
                        }
                        System.out.println("Điểm cao nhất: " + max);
                        System.out.println("Điểm thấp nhất: " + min);
                    }
                    break;

                case 5:
                    if (diemArr != null) {
                        int dat = 0, truot = 0;
                        for (double d : diemArr) {
                            if (d >= 5) dat++;
                            else truot++;
                        }
                        System.out.println("Số SV đạt (>=5): " + dat);
                        System.out.println("Số SV trượt (<5): " + truot);
                    }
                    break;

                case 6:
                    if (diemArr != null) {
                        Arrays.sort(diemArr);
                        System.out.println("Đã sắp xếp tăng dần: " + Arrays.toString(diemArr));
                    }
                    break;

                case 7:
                    if (diemArr != null) {
                        int gioiXuatSac = 0;
                        for (double d : diemArr) {
                            if (d >= 8) gioiXuatSac++;
                        }
                        System.out.println("Số SV Giỏi & Xuất sắc (>=8): " + gioiXuatSac);
                    }
                    break;

                case 8:
                    System.out.println("Tạm biệt!");
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn sai, vui lòng nhập lại!");
            }
        }
    }
}