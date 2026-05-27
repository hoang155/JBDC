package org.example;

import entity.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final List<Message> messageList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    static void main() {
        while (true) {
            System.out.println("Nhập tên người gửi (hoặc 'exit' để thoát):");
            String sender = scanner.nextLine().trim();

            if (sender.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Nhập nội dung tin nhắn:");
            String content = scanner.nextLine().trim();

            // Tự động gán thời gian hiện tại cho tin nhắn
            Message newMessage = new Message(sender, content, LocalDateTime.now());
            messageList.add(newMessage);

            System.out.println("Nhập 'history' để xem lịch sử, hoặc 'filter' để lọc tin nhắn theo người gửi, hoặc 'date' để lọc theo ngày:");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "history" -> showHistory();
                case "filter" -> filterBySender();
                case "date" -> filterByDate();
                default -> {
                    // Nếu nhập ký tự khác hoặc nhấn Enter thì tiếp tục vòng lặp gửi tin nhắn tiếp theo
                }
            }
        }
    }

    // Chức năng 4: Xem lịch sử chat (In toàn bộ)
    private static void showHistory() {
        System.out.println("Lịch sử chat:");
        if (messageList.isEmpty()) {
            System.out.println("(Trống)");
        } else {
            messageList.forEach(System.out::println);
        }
    }

    // Chức năng 5: Lọc tin nhắn theo người gửi bằng Stream API
    private static void filterBySender() {
        System.out.println("Nhập tên người gửi để lọc:");
        String targetSender = scanner.nextLine().trim();

        System.out.println("Tin nhắn từ " + targetSender + ":");
        messageList.stream()
                .filter(m -> m.getSender().equalsIgnoreCase(targetSender)) // Lọc không phân biệt hoa thường
                .forEach(System.out::println);
    }

    // Chức năng 6 & 7: Lọc theo ngày và Xử lý ngoại lệ với try/catch
    private static void filterByDate() {
        while (true) {
            System.out.println("Nhập ngày (dd-MM-yyyy):");
            String dateStr = scanner.nextLine().trim();

            try {
                // Parse chuỗi nhập vào thành đối tượng ngày LocalDate
                java.time.LocalDate targetDate = java.time.LocalDate.parse(dateStr, dateFormatter);

                // In ra tiêu đề đúng định dạng năm-tháng-ngày trong ảnh mẫu (yyyy-MM-dd)
                System.out.println("Tin nhắn trong ngày " + targetDate + ":");

                // Dùng Stream API để lọc tin nhắn trùng ngày
                messageList.stream()
                        .filter(m -> m.getTimestamp().toLocalDate().equals(targetDate))
                        .forEach(System.out::println);

                break; // Thoát khỏi vòng lặp nhập ngày sau khi xử lý thành công

            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày không hợp lệ! Vui lòng nhập lại (ví dụ: 21-05-2025).");
                // Vòng lặp while(true) sẽ giữ người dùng ở lại để nhập lại cho đúng
            }
        }
    }
}
