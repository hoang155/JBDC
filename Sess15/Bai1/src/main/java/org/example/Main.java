package org.example;

import entity.Movie;
import entity.MovieManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        MovieManager<Movie> manager = new MovieManager<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int choice = -1;

        while (choice != 7) {
            System.out.println("\nChọn chức năng:");
            System.out.println("1. Thêm phim");
            System.out.println("2. Xóa phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Hiển thị phim");
            System.out.println("5. Tìm kiếm phim theo tên");
            System.out.println("6. Lọc phim theo rating");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ kí tự xuống dòng thừa

                switch (choice) {
                    case 1: // Thêm phim mới
                        System.out.println("Nhập ID phim:");
                        String id = scanner.nextLine().trim();

                        System.out.println("Nhập tiêu đề phim:");
                        String title = scanner.nextLine().trim();

                        System.out.println("Nhập đạo diễn:");
                        String director = scanner.nextLine().trim();

                        System.out.println("Nhập ngày phát hành (dd-MM-yyyy):");
                        String dateStr = scanner.nextLine().trim();
                        LocalDate releasedDate = LocalDate.parse(dateStr, formatter); // Bắt lỗi định dạng ngày

                        System.out.println("Nhập rating:");
                        double rating = scanner.nextDouble(); // Bắt lỗi nếu nhập chữ thay vì số
                        scanner.nextLine();

                        Movie newMovie = new Movie(id, title, director, releasedDate, rating);
                        manager.add(newMovie);
                        break;

                    case 2: // Xóa phim
                        System.out.println("Nhập ID phim cần xóa:");
                        String idXoa = scanner.nextLine().trim();
                        boolean isDeleted = manager.deleteById(idXoa);
                        if (isDeleted) {
                            System.out.println("Phim đã được xóa thành công.");
                        } else {
                            System.out.println("Không tìm thấy phim muốn xóa !");
                        }
                        break;

                    case 3: // Sửa phim
                        System.out.println("Mời nhập id phim muốn sửa :");
                        String idSua = scanner.nextLine().trim();
                        Movie currentMovie = manager.findById(idSua);

                        if (currentMovie == null) {
                            System.out.println("Không tìm thấy phim với id = " + idSua);
                        } else {
                            System.out.println("Nhập tiêu đề phim:");
                            currentMovie.setTitle(scanner.nextLine().trim());

                            System.out.println("Nhập đạo diễn:");
                            currentMovie.setDirector(scanner.nextLine().trim());

                            System.out.println("Nhập ngày phát hành (dd-MM-yyyy):");
                            String newDateStr = scanner.nextLine().trim();
                            currentMovie.setReleasedDate(LocalDate.parse(newDateStr, formatter));

                            System.out.println("Nhập rating:");
                            currentMovie.setRating(scanner.nextDouble());
                            scanner.nextLine();

                            System.out.println("Cập nhật phim thành công !");
                        }
                        break;

                    case 4: // Hiển thị tất cả phim
                        manager.displayAll();
                        break;

                    case 5: // Tìm kiếm phim theo tên
                        System.out.println("Nhập tiêu đề phim để tìm kiếm:");
                        String searchTitle = scanner.nextLine().trim();
                        manager.searchByTitle(searchTitle);
                        break;

                    case 6: // Lọc phim theo rating
                        // Đề bài trong ảnh 1 ghi "rating > 8.0", còn ảnh minh họa ghi "Nhập rating tối thiểu"
                        // Đoạn này thiết kế động cho phép nhập số mong muốn như ảnh minh họa
                        System.out.println("Nhập rating tối thiểu để lọc:");
                        double minRating = scanner.nextDouble();
                        scanner.nextLine();
                        manager.filterByRating(minRating);
                        break;

                    case 7: // Thoát
                        System.out.println("Chương trình kết thúc!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại số từ 1 đến 7.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Dữ liệu nhập vào sai định dạng số!");
                scanner.nextLine(); // Dọn dẹp hàng đợi tránh vòng lặp vô hạn
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Định dạng ngày tháng không hợp lệ (Bắt buộc phải là dd-MM-yyyy)!");
            }
        }
        scanner.close();
    }
}
