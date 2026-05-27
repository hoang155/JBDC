package org.example;

import entity.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        List<Event> eventList = new ArrayList<>();

        // Định dạng chuẩn bắt buộc đầu vào: dd-MM-yyyy HH:mm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        while (true) {
            // 1. Nhập và kiểm tra tên sự kiện
            String name = "";
            while (true) {
                System.out.print("Nhập tên sự kiện (hoặc 'exit' để thoát):\n");
                name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("Can not enter empty string");
                    continue; // Bắt buộc nhập lại nếu chuỗi rỗng
                }
                break;
            }

            // Nếu người dùng nhập "exit", thoát vòng lặp nhập dữ liệu
            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            // 2. Nhập và xử lý ngoại lệ cho thời gian bắt đầu
            LocalDateTime startDate = null;
            while (true) {
                System.out.print("Nhập thời gian bắt đầu (dd-MM-yyyy HH:mm):\n");
                String startStr = scanner.nextLine().trim();
                try {
                    startDate = LocalDateTime.parse(startStr, formatter);
                    break; // Định dạng đúng -> Thoát vòng lặp nhập thời gian bắt đầu
                } catch (DateTimeParseException e) {
                    System.out.println("Enter not valid date");
                }
            }

            // 3. Nhập và xử lý ngoại lệ cho thời gian kết thúc
            LocalDateTime endDate = null;
            while (true) {
                System.out.print("Nhập thời gian kết thúc (dd-MM-yyyy HH:mm):\n");
                String endStr = scanner.nextLine().trim();
                try {
                    endDate = LocalDateTime.parse(endStr, formatter);

                    // Kiểm tra logic thực tế: thời gian kết thúc phải sau thời gian bắt đầu
                    if (endDate.isBefore(startDate)) {
                        System.out.println("Thời gian kết thúc phải sau thời gian bắt đầu. Vui lòng nhập lại.");
                        continue;
                    }
                    break; // Định dạng đúng và hợp lệ -> Thoát vòng lặp
                } catch (DateTimeParseException e) {
                    System.out.println("Enter not valid date");
                }
            }

            // Tạo đối tượng Event mới và thêm vào danh sách lưu trữ
            Event newEvent = new Event(name, startDate, endDate);
            eventList.add(newEvent);
        }

        // 4. In ra toàn bộ danh sách sự kiện sau khi gõ 'exit'
        System.out.println("Danh sách sự kiện:");
        if (eventList.isEmpty()) {
            System.out.println("(Trống)");
        } else {
            for (Event event : eventList) {
                System.out.println(event);
            }
        }

        scanner.close();
    }
}
