package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // 1. Tạo 3 object Student
        Student s1 = new Student(1, "Nguyễn Văn A", 20, 3.5);
        Student s2 = new Student(2, "Lê Thị B", 21, 3.8);
        Student s3 = new Student(3, "Trần Văn C", 19, 2.9);

        // 2. In thông tin từng sinh viên
        System.out.println("--- Danh sách sinh viên ---");
        s1.printInfo();
        s2.printInfo();
        s3.printInfo();

        // 3. In ra tổng số sinh viên được tạo bằng cách gọi Student.getCount()
        System.out.println("---------------------------");
        System.out.println("Tổng số sinh viên đã tạo: " + Student.getCount());
    }
}