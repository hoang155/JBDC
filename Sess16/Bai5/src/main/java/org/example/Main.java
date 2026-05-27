package org.example;

import entity.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo danh sách ít nhất 10 sinh viên thuộc nhiều chuyên ngành
        List<Student> students = List.of(
                new Student("An", "IT", 8.5),
                new Student("Bình", "IT", 7.0),
                new Student("Cường", "IT", 9.0),
                new Student("Dũng", "Biz", 6.5),
                new Student("Giang", "Biz", 8.0),
                new Student("Hương", "Design", 7.5),
                new Student("Khánh", "Design", 8.2),
                new Student("Linh", "Design", 6.8),
                new Student("Minh", "Design", 9.5),
                new Student("Nam", "Marketing", 7.8)
        );

        System.out.println("=== THỐNG KÊ SINH VIÊN THEO CHUYÊN NGÀNH ===");

        // 2. Sử dụng Collectors.groupingBy và Collectors.counting() để thống kê số lượng
        // Trả về một Map<String, Long> lưu cấu trúc: {Tên_Chuyên_Ngành: Số_Lượng}
        Map<String, Long> majorCountMap = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getMajor,
                        Collectors.counting()
                ));

        // 3. Dùng entrySet().stream().sorted() để sắp xếp kết quả theo số lượng giảm dần
        majorCountMap.entrySet().stream()
                // Map.Entry.comparingByValue() giúp so sánh theo số lượng (Value của Map)
                // .reversed() giúp đảo ngược thứ tự để đạt được sắp xếp GIẢM DẦN
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                // 4. In dữ liệu ra màn hình theo format yêu cầu
                .forEach(entry -> {
                    System.out.println("Chuyên ngành: " + entry.getKey() + " - Số lượng: " + entry.getValue());
                });
    }
}