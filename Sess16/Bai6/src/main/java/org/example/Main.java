package org.example;

import entity.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo danh sách 5 đơn hàng mẫu bằng Stream (kết hợp dữ liệu giao và chưa giao)
        List<Order> orders = Stream.of(
                new Order(101, "Nguyễn Văn A", LocalDate.of(2025, 3, 10), LocalDate.of(2025, 3, 15)),
                new Order(102, "Trần Thị B",   LocalDate.of(2025, 3, 16), LocalDate.of(2025, 3, 20)), // Trong khoảng ngày test
                new Order(103, "Lê Văn C",    LocalDate.of(2025, 3, 18), LocalDate.of(2025, 3, 22)), // Trong khoảng ngày test
                new Order(104, "Phạm Minh D",  LocalDate.of(2025, 3, 22), null),                      // Chưa giao
                new Order(105, "Hoàng Lan E",  LocalDate.of(2025, 3, 25), null)                       // Chưa giao
        ).toList();

        System.out.println("================ TẤT CẢ ĐƠN HÀNG ================");
        printOrders(orders);

        // 2. Liệt kê các đơn hàng ĐÃ ĐƯỢC GIAO (deliveryDate có phần tử bên trong)
        System.out.println("\n========= DANH SÁCH ĐƠN HÀNG ĐÃ GIAO =========");
        List<Order> deliveredOrders = orders.stream()
                .filter(o -> o.getDeliveryDate().isPresent()) // .isPresent() trả về true nếu có ngày giao
                .toList();
        printOrders(deliveredOrders);

        // 3. Liệt kê các đơn hàng CHƯA ĐƯỢC GIAO (deliveryDate trống rỗng)
        System.out.println("\n======== DANH SÁCH ĐƠN HÀNG CHƯA GIAO ========");
        List<Order> pendingOrders = orders.stream()
                .filter(o -> o.getDeliveryDate().isEmpty())   // .isEmpty() trả về true nếu ngày giao null
                .toList();
        printOrders(pendingOrders);

        // 4. Đếm số đơn hàng ĐÃ GIAO trong khoảng thời gian từ 17/03/2025 đến 23/03/2025
        LocalDate startDate = LocalDate.of(2025, 3, 17);
        LocalDate endDate = LocalDate.of(2025, 3, 23);

        long count = orders.stream()
                // Lọc điều kiện 1: Phải là đơn hàng đã giao
                .filter(o -> o.getDeliveryDate().isPresent())
                // Lọc điều kiện 2: Ngày giao nằm trong khoảng [startDate, endDate]
                .filter(o -> {
                    LocalDate dDate = o.getDeliveryDate().get(); // Lấy ngày an toàn từ Optional ra
                    return !dDate.isBefore(startDate) && !dDate.isAfter(endDate);
                })
                .count(); // Sử dụng hàm đầu cuối count() để thống kê số lượng

        System.out.println("\n================ THỐNG KÊ ================");
        System.out.printf("Số đơn hàng đã giao từ %s đến %s là: %d đơn hàng.\n", startDate, endDate, count);
    }

    // Hàm tiện ích: Viết hàm printOrders(List<Order>) theo đúng chỉ dẫn gợi ý bài làm
    private static void printOrders(List<Order> orderList) {
        if (orderList.isEmpty()) {
            System.out.println("(Không có đơn hàng nào thỏa mãn)");
            return;
        }
        orderList.stream()
                .map(Order::toDisplayString) // Ánh xạ sang chuỗi hiển thị chuẩn hóa
                .forEach(System.out::println);
    }
}