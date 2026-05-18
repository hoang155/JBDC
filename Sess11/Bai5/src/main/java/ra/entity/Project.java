package ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Project {
    private String projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee[] employees;
    public enum status {PLANNING, RUNNING, FINISHED};
    private status status;

    public Project() {
    }

    public Project(String projectId, String projectName, LocalDate startDate, LocalDate endDate, Employee[] employees, status status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = employees;
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    public void inputData(Scanner scanner, Project[] arrProject, int index, Employee[] arrEmp, int empIndex) {
        while (true) {
            System.out.print("Nhập mã dự án (P____, ví dụ P0001): ");
            String idInput = scanner.nextLine().trim();
            if (!idInput.matches("^P\\d{4}$")) {
                System.out.println("Lỗi: Mã dự án phải bắt đầu bằng chữ P và có đúng 5 ký tự!");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < index; i++) {
                if (arrProject[i].getProjectId().equals(idInput)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("Lỗi: Mã dự án này đã tồn tại!");
                continue;
            }
            this.projectId = idInput;
            break;
        }

        while (true) {
            System.out.print("Nhập tên dự án (10-50 ký tự, duy nhất): ");
            String nameInput = scanner.nextLine().trim();
            if (nameInput.length() < 10 || nameInput.length() > 50) {
                System.out.println("Lỗi: Tên dự án phải từ 10 đến 50 ký tự!");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < index; i++) {
                if (arrProject[i].getProjectName().equalsIgnoreCase(nameInput)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("Lỗi: Tên dự án này đã tồn tại!");
                continue;
            }
            this.projectName = nameInput;
            break;
        }

        while (true) {
            System.out.print("Nhập ngày bắt đầu (định dạng dd/MM/yyyy): ");
            try {
                this.startDate = LocalDate.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Định dạng ngày không hợp lệ! Vui lòng nhập lại (ví dụ: 18/05/2026).");
            }
        }

        while (true) {
            System.out.print("Nhập ngày kết thúc (định dạng dd/MM/yyyy): ");
            try {
                LocalDate endInput = LocalDate.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (endInput.isBefore(this.startDate)) {
                    System.out.println("Lỗi: Ngày kết thúc phải sau hoặc trùng với ngày bắt đầu (" + startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")!");
                    continue;
                }
                this.endDate = endInput;
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Định dạng ngày không hợp lệ!");
            }
        }

        while (true) {
            System.out.print("Chọn trạng thái (1. PLANNING, 2. RUNNING, 3. FINISHED): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": this.status = status.PLANNING; break;
                case "2": this.status = status.RUNNING; break;
                case "3": this.status = status.FINISHED; break;
                default:
                    System.out.println("Lỗi: Lựa chọn trạng thái không hợp lệ!");
                    continue;
            }
            break;
        }

        if (empIndex > 0) {
            System.out.print("Bạn có muốn gán nhân viên vào dự án ngay bây giờ không? (Y/N): ");
            String addEmpChoice = scanner.nextLine().trim();
            if (addEmpChoice.equalsIgnoreCase("Y")) {
                addEmployeeToProject(scanner, arrEmp, empIndex);
            }
        }
    }
    public void addEmployeeToProject(Scanner scanner, Employee[] arrEmp, int empIndex) {
        System.out.println("\n--- DANH SÁCH NHÂN VIÊN SẴN CÓ ---");
        for (int i = 0; i < empIndex; i++) {
            System.out.print("[" + (i + 1) + "] ");
            arrEmp[i].displayData();
        }

        while (true) {
            System.out.print("Nhập mã nhân viên muốn thêm vào dự án (hoặc gõ 'STOP' để dừng): ");
            String empId = scanner.nextLine().trim();
            if (empId.equalsIgnoreCase("STOP")) {
                break;
            }

            // Tìm nhân viên trong danh sách tổng
            Employee foundEmp = null;
            for (int i = 0; i < empIndex; i++) {
                if (arrEmp[i].getEmployeeId().equals(empId)) {
                    foundEmp = arrEmp[i];
                    break;
                }
            }

            if (foundEmp == null) {
                System.out.println("Lỗi: Không tìm thấy nhân viên có mã " + empId);
                continue;
            }

            // Kiểm tra xem nhân viên đã có trong dự án này chưa
            boolean isAssigned = false;
            for (Employee e : this.employees) {
                if (e.getEmployeeId().equals(empId)) {
                    isAssigned = true;
                    break;
                }
            }

            if (isAssigned) {
                System.out.println("⚠️ Thông báo: Nhân viên này đã tham gia dự án từ trước.");
            } else {
                Employee[] newArray = new Employee[this.employees.length + 1];
                System.arraycopy(this.employees, 0, newArray, 0, this.employees.length);
                newArray[newArray.length - 1] = foundEmp;
                this.employees = newArray;
                System.out.println("✅ Đã thêm nhân viên " + foundEmp.getEmployeeName() + " vào dự án.");
            }
        }
    }
    public void displayData() {
        System.out.printf("Mã DA: %-6s | Tên DA: %-25s | Từ: %s -> Đến: %s | Trạng thái: %-10s\n",
                projectId, projectName, startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), status);
        if (employees.length == 0) {
            System.out.println("   (Chưa có nhân viên nào tham gia dự án này)");
        } else {
            System.out.println("   Danh sách thành viên tham gia:");
            for (Employee e : employees) {
                System.out.print("     + ");
                e.displayData();
            }
        }
    }

}
