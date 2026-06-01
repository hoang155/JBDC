package org.example;
import entity.Course;
import entity.Student;

import java.sql.*;

public class StudentManager {
    private final String url = "jdbc:mysql://localhost:3306/school_db?useSSL=false&serverTimezone=UTC";
    private final String user = "root";       // Thay thế bằng tài khoản MySQL của bạn
    private final String password = "password"; // Thay thế bằng mật khẩu MySQL của bạn

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Kiểm tra trùng Email sinh viên
    private boolean isEmailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM student WHERE LOWER(email) = LOWER(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Kiểm tra trùng Tiêu đề khóa học
    private boolean isCourseTitleExists(String title) throws SQLException {
        String sql = "SELECT COUNT(*) FROM course WHERE LOWER(title) = LOWER(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Kiểm tra tồn tại ID Sinh viên
    public boolean isStudentIdExists(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Kiểm tra tồn tại ID Khóa học
    public boolean isCourseIdExists(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM course WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Chức năng 1: Thêm sinh viên mới (Có kiểm tra trùng Email)
    public void addStudent(Student student) {
        try {
            if (isEmailExists(student.getEmail())) {
                System.out.println("❌ Lỗi: Email '" + student.getEmail() + "' đã tồn tại trên hệ thống!");
                return;
            }
            String sql = "INSERT INTO student (name, email) VALUES (?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, student.getName());
                pstmt.setString(2, student.getEmail());
                pstmt.executeUpdate();
                System.out.println("🎉 Thêm sinh viên mới thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Chức năng 2: Thêm khóa học mới (Có kiểm tra trùng tiêu đề)
    public void addCourse(Course course) {
        try {
            if (isCourseTitleExists(course.getTitle())) {
                System.out.println("❌ Lỗi: Khóa học '" + course.getTitle() + "' đã tồn tại!");
                return;
            }
            String sql = "INSERT INTO course (title, credits) VALUES (?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, course.getTitle());
                pstmt.setInt(2, course.getCredits());
                pstmt.executeUpdate();
                System.out.println("🎉 Thêm khóa học mới thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Chức năng 3: Ghi danh sinh viên vào khóa học (Kiểm tra sự tồn tại của cả hai)
    public void enrollStudent(int studentId, int courseId) {
        try {
            if (!isStudentIdExists(studentId)) {
                System.out.println("❌ Lỗi: Không tồn tại sinh viên với ID = " + studentId);
                return;
            }
            if (!isCourseIdExists(courseId)) {
                System.out.println("❌ Lỗi: Không tồn tại khóa học với ID = " + courseId);
                return;
            }

            String sql = "INSERT INTO enrollment (student_id, course_id) VALUES (?, ?)"
                    + " ON DUPLICATE KEY UPDATE student_id = student_id"; // Tránh crash nếu đã đăng ký rồi
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, studentId);
                pstmt.setInt(2, courseId);
                pstmt.executeUpdate();
                System.out.println("🎉 Ghi danh sinh viên vào khóa học thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi ghi danh: " + e.getMessage());
        }
    }

    // Chức năng 4: Hiển thị danh sách sinh viên cùng điểm các khóa học đã đăng ký
    public void listStudentsAndGrades() {
        String sql = "SELECT s.id AS std_id, s.name AS std_name, s.email, " +
                "c.title AS course_title, e.grade " +
                "FROM student s " +
                "LEFT JOIN enrollment e ON s.id = e.student_id " +
                "LEFT JOIN course c ON e.course_id = c.id " +
                "ORDER BY s.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n==================== DANH SÁCH SINH VIÊN & ĐIỂM SỐ ====================");
            boolean hasData = false;
            int currentStudentId = -1;

            while (rs.next()) {
                hasData = true;
                int stdId = rs.getInt("std_id");

                if (stdId != currentStudentId) {
                    System.out.printf("\n[SV ID: %d] Tên: %-20s | Email: %s\n",
                            stdId, rs.getString("std_name"), rs.getString("email"));
                    currentStudentId = stdId;
                }

                String courseTitle = rs.getString("course_title");
                if (courseTitle != null) {
                    double grade = rs.getDouble("grade");
                    // Kiểm tra xem cột grade có phải là NULL trong DB không để hiển thị phù hợp
                    String gradeStr = rs.wasNull() ? "Chưa có điểm" : String.format("%.2f", grade);
                    System.out.printf("   └─> Khóa học: %-25s | Điểm: %s\n", courseTitle, gradeStr);
                } else {
                    System.out.println("   └─> (Chưa đăng ký học phần nào)");
                }
            }
            if (!hasData) {
                System.out.println("Hệ thống chưa có dữ liệu sinh viên.");
            }
            System.out.println("\n=======================================================================");

        } catch (SQLException e) {
            System.out.println("❌ Lỗi lấy danh sách dữ liệu: " + e.getMessage());
        }
    }

    // Chức năng 5: Cập nhật điểm số cho sinh viên trong một khóa học cụ thể
    public void updateStudentGrade(int studentId, int courseId, double grade) {
        try {
            String checkSql = "SELECT COUNT(*) FROM enrollment WHERE student_id = ? AND course_id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, studentId);
                checkStmt.setInt(2, courseId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        System.out.println("❌ Lỗi: Sinh viên này chưa đăng ký tham gia lớp học phần này!");
                        return;
                    }
                }
            }

            String updateSql = "UPDATE enrollment SET grade = ? WHERE student_id = ? AND course_id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setDouble(1, grade);
                pstmt.setInt(2, studentId);
                pstmt.setInt(3, courseId);
                pstmt.executeUpdate();
                System.out.println("🎉 Cập nhật điểm cho sinh viên thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cập nhật điểm số: " + e.getMessage());
        }
    }
}