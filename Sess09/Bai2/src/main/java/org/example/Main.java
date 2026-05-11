package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Tạo danh sách bằng ArrayList
        ArrayList<Book> listBook = new ArrayList<>();

        // Thêm các object Book vào danh sách
        listBook.add(new Book("Đắc Nhân Tâm", "Dale Carnegie", 85000));
        listBook.add(new Book("Nhà Giả Kim", "Paulo Coelho", 70000));
        listBook.add(new Book("Dế Mèn Phiêu Lưu Ký", "Tô Hoài", 55000));

        // Duyệt danh sách bằng vòng lặp for-each
        System.out.println("--- DANH SÁCH SÁCH (Sử dụng ArrayList) ---");
        for (Book b : listBook) {
            b.printInfo();
        }
    }
}