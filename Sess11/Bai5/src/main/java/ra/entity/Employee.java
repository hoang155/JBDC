package ra.entity;
import java.util.Scanner;

public class Employee {
    private String employeeId;
    private String employeeName;
    public enum role {DEV,TESTER,PM,BA}
    private role role;
    private double salary;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, role role, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputData(Scanner scanner, Employee[] arrEmp, int index) {
        // 1. Nhập và validate Employee ID
        while (true) {
            System.out.print("Nhập mã nhân viên (E____, ví dụ E0001): ");
            String idInput = scanner.nextLine().trim();
            if (!idInput.matches("^E\\d{4}$")) {
                System.out.println("Lỗi: Mã nhân viên phải bắt đầu bằng chữ E và có đúng 5 ký tự!");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < index; i++) {
                if (arrEmp[i].getEmployeeId().equals(idInput)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("Lỗi: Mã nhân viên này đã tồn tại trong hệ thống!");
                continue;
            }
            this.employeeId = idInput;
            break;
        }

        // 2. Nhập và validate Employee Name
        while (true) {
            System.out.print("Nhập tên nhân viên (6-30 ký tự): ");
            String nameInput = scanner.nextLine().trim();
            if (nameInput.length() < 6 || nameInput.length() > 30) {
                System.out.println("❌ Lỗi: Tên nhân viên phải có độ dài từ 6 đến 30 ký tự!");
                continue;
            }
            this.employeeName = nameInput;
            break;
        }

        // 3. Nhập và validate Role (Enum)
        while (true) {
            System.out.print("Chọn vai trò (1. DEV, 2. TESTER, 3. PM, 4. BA): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": this.role = role.DEV; break;
                case "2": this.role = role.TESTER; break;
                case "3": this.role = role.PM; break;
                case "4": this.role = role.BA; break;
                default:
                    System.out.println("Lỗi: Lựa chọn không hợp lệ! Vui lòng chọn từ 1 đến 4.");
                    continue;
            }
            break;
        }

        while (true) {
            System.out.print("Nhập lương nhân viên (> 0): ");
            try {
                double salaryInput = Double.parseDouble(scanner.nextLine().trim());
                if (salaryInput <= 0) {
                    System.out.println("Lỗi: Lương nhân viên phải lớn hơn 0!");
                    continue;
                }
                this.salary = salaryInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Lương phải là một số thực hợp lệ!");
            }
        }
    }

    public void displayData() {
        System.out.printf("Mã NV: %-6s | Tên NV: %-20s | Vai trò: %-8s | Lương: %,.2f VND\n",
                employeeId, employeeName, role, salary);
    }
}
