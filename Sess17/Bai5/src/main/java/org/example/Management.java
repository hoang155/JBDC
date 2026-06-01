package org.example;
import entity.Employee;
import entity.Project;

import java.sql.*;

public class Management {
    private final String url = "jdbc:mysql://localhost:3306/company_db?useSSL=false&serverTimezone=UTC";
    private final String user = "root";       // Thay thế bằng username MySQL của bạn
    private final String password = "password"; // Thay thế bằng password MySQL của bạn

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Kiểm tra trùng tên Nhân viên
    private boolean isEmployeeNameExists(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM employee WHERE LOWER(name) = LOWER(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Kiểm tra trùng tên Dự án
    private boolean isProjectNameExists(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM project WHERE LOWER(name) = LOWER(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Kiểm tra tồn tại ID Nhân viên
    public boolean isEmployeeIdExists(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM employee WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Kiểm tra tồn tại ID Dự án
    public boolean isProjectIdExists(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM project WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Chức năng 1: Thêm nhân viên mới (Có kiểm tra trùng tên)
    public void addEmployee(Employee employee) {
        try {
            if (isEmployeeNameExists(employee.getName())) {
                System.out.println("❌ Lỗi: Tên nhân viên '" + employee.getName() + "' đã tồn tại!");
                return;
            }
            String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, employee.getName());
                pstmt.setString(2, employee.getDepartment());
                pstmt.setDouble(3, employee.getSalary());
                pstmt.executeUpdate();
                System.out.println("🎉 Thêm nhân viên mới thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Chức năng 2: Thêm dự án mới (Có kiểm tra trùng tên)
    public void addProject(Project project) {
        try {
            if (isProjectNameExists(project.getName())) {
                System.out.println("❌ Lỗi: Tên dự án '" + project.getName() + "' đã tồn tại!");
                return;
            }
            String sql = "INSERT INTO project (name, budget) VALUES (?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, project.getName());
                pstmt.setDouble(2, project.getBudget());
                pstmt.executeUpdate();
                System.out.println("🎉 Thêm dự án mới thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Chức năng 3: Gán nhân viên vào dự án (Kiểm tra tồn tại nhân viên & dự án)
    public void assignEmployeeToProject(int employeeId, int projectId, String role) {
        try {
            if (!isEmployeeIdExists(employeeId)) {
                System.out.println("❌ Lỗi: Không tồn tại nhân viên với ID = " + employeeId);
                return;
            }
            if (!isProjectIdExists(projectId)) {
                System.out.println("❌ Lỗi: Không tồn tại dự án với ID = " + projectId);
                return;
            }

            String sql = "INSERT INTO assignment (employee_id, project_id, role) VALUES (?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE role = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, employeeId);
                pstmt.setInt(2, projectId);
                pstmt.setString(3, role);
                pstmt.setString(4, role); // Nếu đã phân công rồi thì cập nhật lại vai trò (role)
                pstmt.executeUpdate();
                System.out.println("🎉 Gán nhân viên vào dự án thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi phân công dự án: " + e.getMessage());
        }
    }

    // Chức năng 4: Hiển thị nhân viên và các dự án họ tham gia
    public void listEmployeesAndProjects() {
        String sql = "SELECT e.id AS emp_id, e.name AS emp_name, e.department, " +
                "p.name AS proj_name, a.role " +
                "FROM employee e " +
                "LEFT JOIN assignment a ON e.id = a.employee_id " +
                "LEFT JOIN project p ON a.project_id = p.id " +
                "ORDER BY e.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n==================== DANH SÁCH NHÂN VIÊN VÀ DỰ ÁN ====================");
            boolean hasData = false;
            int currentEmpId = -1;

            while (rs.next()) {
                hasData = true;
                int empId = rs.getInt("emp_id");

                // Gom nhóm hiển thị theo từng nhân viên cho dễ nhìn trên Console
                if (empId != currentEmpId) {
                    System.out.printf("\n[NV ID: %d] Tên: %-20s | Phòng ban: %-15s\n",
                            empId, rs.getString("emp_name"), rs.getString("department"));
                    currentEmpId = empId;
                }

                String projName = rs.getString("proj_name");
                if (projName != null) {
                    System.out.printf("   └─> Tham gia dự án: %-20s | Vai trò: %s\n",
                            projName, rs.getString("role"));
                } else {
                    System.out.println("   └─> (Chưa tham gia dự án nào)");
                }
            }
            if (!hasData) {
                System.out.println("Hệ thống chưa có dữ liệu nhân viên.");
            }
            System.out.println("\n======================================================================");

        } catch (SQLException e) {
            System.out.println("❌ Lỗi lấy danh sách dữ liệu: " + e.getMessage());
        }
    }

    // Chức năng 5: Cập nhật lương nhân viên (Kiểm tra nếu nhân viên tồn tại)
    public void updateEmployeeSalary(int employeeId, double newSalary) {
        try {
            if (!isEmployeeIdExists(employeeId)) {
                System.out.println("❌ Lỗi: Không tìm thấy nhân viên nào có ID = " + employeeId);
                return;
            }

            String sql = "UPDATE employee SET salary = ? WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, newSalary);
                pstmt.setInt(2, employeeId);
                pstmt.executeUpdate();
                System.out.println("🎉 Cập nhật lương nhân viên thành công!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi cập nhật lương: " + e.getMessage());
        }
    }
}