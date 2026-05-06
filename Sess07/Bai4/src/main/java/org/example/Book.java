package org.example;

public class Book {
    // Khai báo 3 thuộc tính với phạm vi truy cập public (+)
    public String title;
    public String author;
    public double price;

    // Viết phương thức printInfo() để in ra thông tin quyển sách
    public void printInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
    }
}
