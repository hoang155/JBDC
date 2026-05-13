package org.example;
import java.util.Scanner;

public class Student {
    // 1. Thuộc tính thông thường (instance variables)
    private int id;
    private String name;
    private double gpa;

    // 2. Thuộc tính static (class variable) - Dùng chung cho toàn bộ lớp
    public static int countStudent = 0;

    // 3. Hằng số cho mỗi đối tượng
    public final double SCORE_FACTOR = 0.25;

    // 4. Constructor không tham số
    public Student() {
        countStudent++; // Mỗi lần tạo mới một sinh viên, tăng biến đếm lên 1
    }

    // 5. Constructor có 3 tham số
    public Student(int id, String name, double gpa) {
        this(); // Gọi lại constructor không tham số để tăng countStudent
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // 6. Các phương thức getter và xử lý
    public double getGpa() {
        return gpa;
    }

    public static int getTotalStudent() {
        return countStudent;
    }

    // Phương thức nhập thông tin
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập ID: ");
        this.id = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập tên: ");
        this.name = sc.nextLine();
        System.out.print("Nhập GPA: ");
        this.gpa = Double.parseDouble(sc.nextLine());
    }

    // Phương thức in thông tin
    public void print() {
        System.out.printf("ID: %d | Tên: %-20s | GPA: %.2f | Quy đổi (x%.2f): %.2f%n",
                id, name, gpa, SCORE_FACTOR, (gpa * SCORE_FACTOR));
    }
}