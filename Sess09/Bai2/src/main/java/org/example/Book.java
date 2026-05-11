package org.example;

public class Book {
    // 1. Khai báo 3 thuộc tính private
    private String title;
    private String author;
    private double price;

    // 2. Constructor (khuyến khích) để gán giá trị khi tạo object
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // 3. Phương thức public để in thông tin
    public void printInfo() {
        System.out.printf("Sách: %-20s | Tác giả: %-15s | Giá: %.2f VNĐ%n",
                title, author, price);
    }
}