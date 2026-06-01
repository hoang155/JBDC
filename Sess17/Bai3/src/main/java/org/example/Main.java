package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Thêm sách mới");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách theo tác giả");
            System.out.println("5. Hiển thị tất cả sách");
            System.out.println("6. Thoát hệ thống");
            System.out.print("Vui lòng chọn chức năng (1-6): ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên sách: ");
                        String title = sc.nextLine().trim();
                        System.out.print("Nhập tác giả: ");
                        String author = sc.nextLine().trim();

                        if (title.isEmpty() || author.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Tên sách và Tác giả không được bỏ trống!");
                            break;
                        }

                        System.out.print("Nhập năm xuất bản: ");
                        int year = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập giá tiền: ");
                        double price = Double.parseDouble(sc.nextLine().trim());

                        if (year <= 0 || price < 0) {
                            System.out.println("⚠️ Lỗi: Năm xuất bản và Giá tiền phải lớn hơn 0!");
                            break;
                        }

                        Book newBook = new Book(title, author, year, price);
                        manager.addBook(newBook);
                        break;

                    case 2:
                        System.out.print("Nhập mã ID sách cần cập nhật: ");
                        int uId = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập tên sách mới: ");
                        String uTitle = sc.nextLine().trim();
                        System.out.print("Nhập tác giả mới: ");
                        String uAuthor = sc.nextLine().trim();

                        if (uTitle.isEmpty() || uAuthor.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Thông tin cập nhật không được bỏ trống!");
                            break;
                        }

                        System.out.print("Nhập năm xuất bản mới: ");
                        int uYear = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nhập giá mới: ");
                        double uPrice = Double.parseDouble(sc.nextLine().trim());

                        Book updatedBook = new Book(uTitle, uAuthor, uYear, uPrice);
                        manager.updateBook(uId, updatedBook);
                        break;

                    case 3:
                        System.out.print("Nhập mã ID sách cần xóa: ");
                        int dId = Integer.parseInt(sc.nextLine().trim());
                        manager.deleteBook(dId);
                        break;

                    case 4:
                        System.out.print("Nhập tên tác giả muốn tìm kiếm: ");
                        String searchAuthor = sc.nextLine().trim();
                        if (searchAuthor.isEmpty()) {
                            System.out.println("⚠️ Lỗi: Từ khóa tìm kiếm không được rỗng!");
                            break;
                        }
                        manager.findBooksByAuthor(searchAuthor);
                        break;

                    case 5:
                        manager.listAllBooks();
                        break;

                    case 6:
                        System.out.println("Cảm ơn bạn đã sử dụng chương trình quản lý thư viện. Tạm biệt!");
                        sc.close();
                        return;

                    default:
                        System.out.println("⚠️ Lựa chọn không hợp lệ. Vui lòng nhập số từ 1 đến 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi dữ liệu nhập: Lựa chọn, ID, Năm phải là số nguyên và Giá phải là một số thực!");
            } catch (Exception e) {
                System.out.println("❌ Đã xảy ra lỗi hệ thống: " + e.getMessage());
            }
        }
    }
}