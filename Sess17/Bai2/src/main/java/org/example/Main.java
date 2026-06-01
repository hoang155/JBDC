package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookManagement manager = new BookManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- BOOK MANAGEMENT MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = sc.nextLine().trim();
                        if (title.isEmpty()) {
                            System.out.println("Lỗi: Tên sách không được để trống!");
                            break;
                        }
                        System.out.print("Enter author: ");
                        String author = sc.nextLine().trim();
                        System.out.print("Enter price: ");
                        double price = Double.parseDouble(sc.nextLine().trim());
                        if (price < 0) {
                            System.out.println("Lỗi: Giá sách không được âm!");
                            break;
                        }

                        manager.addBook(title, author, price);
                        break;

                    case 2:
                        manager.listBooks();
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        int uId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter new title: ");
                        String uTitle = sc.nextLine().trim();
                        if (uTitle.isEmpty()) {
                            System.out.println("Lỗi: Tên sách không được để trống!");
                            break;
                        }
                        System.out.print("Enter new author: ");
                        String uAuthor = sc.nextLine().trim();
                        System.out.print("Enter new price: ");
                        double uPrice = Double.parseDouble(sc.nextLine().trim());
                        if (uPrice < 0) {
                            System.out.println("Lỗi: Giá sách mới không được âm!");
                            break;
                        }

                        manager.updateBook(uId, uTitle, uAuthor, uPrice);
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        int dId = Integer.parseInt(sc.nextLine().trim());
                        manager.deleteBook(dId);
                        break;

                    case 5:
                        System.out.println("Đang thoát chương trình quản lý sách. Tạm biệt!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập từ 1 đến 5!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi nhập liệu: Lựa chọn, ID phải là số nguyên và Giá phải là số thực hợp lệ!");
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }
}