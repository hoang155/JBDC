package org.example;

public class Student {
    // Thuộc tính private
    private int id;
    private String fullName;
    private int age;
    private double gpa;

    // Biến static để đếm số sinh viên
    private static int count = 0;

    // Hằng số final
    public final double MIN_GPA = 0.0;
    public final double MAX_GPA = 4.0;

    // Constructor có tham số
    public Student(int id, String fullName, int age, double gpa) {
        // Dùng this để phân biệt thuộc tính và tham số
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gpa = gpa;

        // Tăng giá trị static count khi tạo sinh viên mới
        count++;
    }

    // Phương thức in thông tin
    public void printInfo() {
        System.out.printf("ID: %d | Tên: %-15s | Tuổi: %d | GPA: %.1f%n",
                id, fullName, age, gpa);
    }

    // Phương thức static để lấy tổng số sinh viên
    public static int getCount() {
        return count;
    }
}