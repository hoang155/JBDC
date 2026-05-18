package ra.presentation;

import ra.entity.Employee;
import ra.entity.Project;

import java.util.Scanner;

public class ProjectManagement {
    // Khởi tạo các mảng tĩnh chứa dữ liệu tối đa 100 thực thể
    private static final Employee[] arrEmp = new Employee[100];
    private static int empIndex = 0;

    private static final Project[] arrProject = new Project[100];
    private static int projectIndex = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n==================== QUẢN LÝ DỰ ÁN ====================");
            System.out.println("1. Quản lý nhân viên");
            System.out.println("2. Quản lý dự án");
            System.out.println("3. Thoát");
            System.out.println("=======================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    displayEmployeeMenu(input);
                    break;
                case "2":
                    displayProjectMenu(input);
                    break;
                case "3":
                    System.out.println("👋 Cảm ơn bạn đã sử dụng hệ thống quản lý! Tạm biệt.");
                    System.exit(0);
                default:
                    System.out.println("Lỗi: Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    // ================= MENU QUẢN LÝ NHÂN VIÊN =================
    private static void displayEmployeeMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================== QUẢN LÝ NHÂN VIÊN ====================");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Hiển thị danh sách nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Tìm kiếm nhân viên theo tên");
            System.out.println("6. Sắp xếp nhân viên theo lương giảm dần");
            System.out.println("7. Thoát");
            System.out.println("===========================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    if (empIndex >= arrEmp.length) {
                        System.out.println("Lỗi: Bộ nhớ danh sách nhân viên đã đầy!");
                        break;
                    }
                    Employee emp = new Employee();
                    emp.inputData(scanner, arrEmp, empIndex);
                    arrEmp[empIndex] = emp;
                    empIndex++;
                    System.out.println("✅ Thêm nhân viên mới thành công!");
                    break;
                case "2":
                    if (empIndex == 0) {
                        System.out.println("⚠Thông báo: Danh sách nhân viên đang trống.");
                    } else {
                        System.out.println("\n--- DANH SÁCH NHÂN VIÊN ---");
                        for (int i = 0; i < empIndex; i++) {
                            arrEmp[i].displayData();
                        }
                    }
                    break;
                case "3":
                    updateEmployee(scanner);
                    break;
                case "4":
                    deleteEmployee(scanner);
                    break;
                case "5":
                    searchEmployeeByName(scanner);
                    break;
                case "6":
                    sortEmployeeBySalaryDescending();
                    break;
                case "7":
                    return; // Quay lại menu chính
                default:
                    System.out.println("Lỗi: Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Nhập mã nhân viên cần cập nhật: ");
        String id = scanner.nextLine().trim();
        int foundIdx = -1;
        for (int i = 0; i < empIndex; i++) {
            if (arrEmp[i].getEmployeeId().equals(id)) {
                foundIdx = i;
                break;
            }
        }
        if (foundIdx == -1) {
            System.out.println("Lỗi: Không tìm thấy nhân viên có mã " + id);
            return;
        }

        System.out.println("--- Nhập thông tin mới cho nhân viên " + id + " ---");
        // Để tránh trùng lặp kiểm tra ID với chính nó, tạo mảng tạm loại trừ phần tử hiện tại
        Employee[] tempArray = new Employee[empIndex - 1];
        int k = 0;
        for (int i = 0; i < empIndex; i++) {
            if (i != foundIdx) {
                tempArray[k++] = arrEmp[i];
            }
        }

        Employee updatedEmp = new Employee();
        updatedEmp.inputData(scanner, tempArray, tempArray.length);
        arrEmp[foundIdx] = updatedEmp;
        System.out.println("✅ Cập nhật thông tin nhân viên thành công!");
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Nhập mã nhân viên cần xóa: ");
        String id = scanner.nextLine().trim();
        int foundIdx = -1;
        for (int i = 0; i < empIndex; i++) {
            if (arrEmp[i].getEmployeeId().equals(id)) {
                foundIdx = i;
                break;
            }
        }
        if (foundIdx == -1) {
            System.out.println("Lỗi: Không tìm thấy nhân viên cần xóa!");
            return;
        }

        // Xóa nhân viên bằng cách dịch chuyển phần tử mảng
        for (int i = foundIdx; i < empIndex - 1; i++) {
            arrEmp[i] = arrEmp[i + 1];
        }
        arrEmp[empIndex - 1] = null;
        empIndex--;
        System.out.println("✅ Đã xóa nhân viên thành công khỏi danh sách hệ thống.");
    }

    private static void searchEmployeeByName(Scanner scanner) {
        System.out.print("Nhập từ khóa tên nhân viên muốn tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
        for (int i = 0; i < empIndex; i++) {
            if (arrEmp[i].getEmployeeName().toLowerCase().contains(keyword)) {
                arrEmp[i].displayData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("⚠Không tìm thấy nhân viên nào phù hợp với từ khóa.");
        }
    }

    private static void sortEmployeeBySalaryDescending() {
        for (int i = 0; i < empIndex - 1; i++) {
            for (int j = i + 1; j < empIndex; j++) {
                if (arrEmp[i].getSalary() < arrEmp[j].getSalary()) {
                    Employee temp = arrEmp[i];
                    arrEmp[i] = arrEmp[j];
                    arrEmp[j] = temp;
                }
            }
        }
        System.out.println("✅ Đã sắp xếp danh sách nhân viên theo mức lương giảm dần!");
    }


    // ================= MENU QUẢN LÝ DỰ ÁN =================
    private static void displayProjectMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================== QUẢN LÝ DỰ ÁN ====================");
            System.out.println("1. Thêm dự án");
            System.out.println("2. Hiển thị danh sách dự án");
            System.out.println("3. Cập nhật thông tin dự án");
            System.out.println("4. Xóa dự án (chỉ khi chưa có nhân viên tham gia)");
            System.out.println("5. Thêm nhân viên vào dự án");
            System.out.println("6. Tìm dự án theo tên");
            System.out.println("7. Thống kê số lượng nhân viên theo vai trò trong từng dự án");
            System.out.println("8. Tìm dự án đang chạy và gần kết thúc nhất");
            System.out.println("9. Thoát");
            System.out.println("=======================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    if (projectIndex >= arrProject.length) {
                        System.out.println("Lỗi: Bộ nhớ danh sách dự án đã đầy!");
                        break;
                    }
                    Project proj = new Project();
                    proj.inputData(scanner, arrProject, projectIndex, arrEmp, empIndex);
                    arrProject[projectIndex] = proj;
                    projectIndex++;
                    System.out.println("✅ Thêm dự án mới thành công!");
                    break;
                case "2":
                    if (projectIndex == 0) {
                        System.out.println("⚠️ Thông báo: Danh sách dự án đang trống.");
                    } else {
                        System.out.println("\n--- DANH SÁCH DỰ ÁN ---");
                        for (int i = 0; i < projectIndex; i++) {
                            arrProject[i].displayData();
                            System.out.println("-------------------------------------------------------");
                        }
                    }
                    break;
                case "3":
                    updateProject(scanner);
                    break;
                case "4":
                    deleteProject(scanner);
                    break;
                case "5":
                    addEmployeeToExistingProject(scanner);
                    break;
                case "6":
                    searchProjectByName(scanner);
                    break;
                case "7":
                    reportRoleCountInProjects();
                    break;
                case "8":
                    findRunningProjectNearestEnd();
                    break;
                case "9":
                    return; // Quay lại menu chính
                default:
                    System.out.println("Lỗi: Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void updateProject(Scanner scanner) {
        System.out.print("Nhập mã dự án cần cập nhật: ");
        String id = scanner.nextLine().trim();
        int foundIdx = -1;
        for (int i = 0; i < projectIndex; i++) {
            if (arrProject[i].getProjectId().equals(id)) {
                foundIdx = i;
                break;
            }
        }
        if (foundIdx == -1) {
            System.out.println("Lỗi: Không tìm thấy dự án có mã " + id);
            return;
        }

        // Tạo mảng loại trừ dự án hiện tại để tránh validate trùng lặp tên chính nó
        Project[] tempProjArray = new Project[projectIndex - 1];
        int k = 0;
        for (int i = 0; i < projectIndex; i++) {
            if (i != foundIdx) {
                tempProjArray[k++] = arrProject[i];
            }
        }

        System.out.println("--- Nhập thông tin mới cho dự án " + id + " ---");
        Project updatedProj = new Project();
        updatedProj.inputData(scanner, tempProjArray, tempProjArray.length, arrEmp, empIndex);

        // Giữ lại danh sách nhân viên cũ đã tham gia từ trước khi update thông tin chung
        updatedProj.setEmployees(arrProject[foundIdx].getEmployees());
        arrProject[foundIdx] = updatedProj;
        System.out.println("✅ Cập nhật thông tin dự án thành công!");
    }

    private static void deleteProject(Scanner scanner) {
        System.out.print("Nhập mã dự án cần xóa: ");
        String id = scanner.nextLine().trim();
        int foundIdx = -1;
        for (int i = 0; i < projectIndex; i++) {
            if (arrProject[i].getProjectId().equals(id)) {
                foundIdx = i;
                break;
            }
        }
        if (foundIdx == -1) {
            System.out.println("Lỗi: Không tìm thấy dự án cần xóa!");
            return;
        }

        // Kiểm tra điều kiện: Chỉ cho phép xóa khi chưa có nhân viên tham gia
        if (arrProject[foundIdx].getEmployees().length > 0) {
            System.out.println("Lỗi: Dự án này đã có nhân viên tham gia, không thể xóa!");
            return;
        }

        for (int i = foundIdx; i < projectIndex - 1; i++) {
            arrProject[i] = arrProject[i + 1];
        }
        arrProject[projectIndex - 1] = null;
        projectIndex--;
        System.out.println("Đã xóa dự án thành công!");
    }

    private static void addEmployeeToExistingProject(Scanner scanner) {
        System.out.print("Nhập mã dự án cần thêm nhân viên: ");
        String id = scanner.nextLine().trim();
        Project selectedProject = null;
        for (int i = 0; i < projectIndex; i++) {
            if (arrProject[i].getProjectId().equals(id)) {
                selectedProject = arrProject[i];
                break;
            }
        }
        if (selectedProject == null) {
            System.out.println("Lỗi: Không tìm thấy dự án!");
            return;
        }

        if (empIndex == 0) {
            System.out.println("Lỗi: Hệ thống chưa có nhân viên nào để gán.");
            return;
        }

        selectedProject.addEmployeeToProject(scanner, arrEmp, empIndex);
    }

    private static void searchProjectByName(Scanner scanner) {
        System.out.print("Nhập từ khóa tên dự án cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        System.out.println("\n--- KẾT QUẢ TÌM KIẾM DỰ ÁN ---");
        for (int i = 0; i < projectIndex; i++) {
            if (arrProject[i].getProjectName().toLowerCase().contains(keyword)) {
                arrProject[i].displayData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("⚠Không tìm thấy dự án nào phù hợp.");
        }
    }

    private static void reportRoleCountInProjects() {
        if (projectIndex == 0) {
            System.out.println("⚠Thông báo: Không có dự án nào để thống kê.");
            return;
        }
        System.out.println("\n===== THỐNG KÊ NHÂN VIÊN THEO VAI TRÒ TRONG TỪNG DỰ ÁN =====");
        for (int i = 0; i < projectIndex; i++) {
            Project p = arrProject[i];
            int devCount = 0, testerCount = 0, pmCount = 0, baCount = 0;

            for (Employee e : p.getEmployees()) {
                if (e.getRole() == Employee.role.DEV) devCount++;
                else if (e.getRole() == Employee.role.TESTER) testerCount++;
                else if (e.getRole() == Employee.role.PM) pmCount++;
                else if (e.getRole() == Employee.role.BA) baCount++;
            }

            System.out.println("Dự án: " + p.getProjectName() + " [" + p.getProjectId() + "]");
            System.out.println("  + DEV   : " + devCount + " nhân viên");
            System.out.println("  + TESTER: " + testerCount + " nhân viên");
            System.out.println("  + PM    : " + pmCount + " nhân viên");
            System.out.println("  + BA    : " + baCount + " nhân viên");
            System.out.println("------------------------------------------------------------");
        }
    }

    private static void findRunningProjectNearestEnd() {
        Project targetProject = null;
        for (int i = 0; i < projectIndex; i++) {
            if (arrProject[i].getStatus() == Project.status.RUNNING) {
                if (targetProject == null) {
                    targetProject = arrProject[i];
                } else {
                    // Dự án gần kết thúc nhất là dự án có endDate nhỏ nhất (sớm nhất)
                    if (arrProject[i].getEndDate().isBefore(targetProject.getEndDate())) {
                        targetProject = arrProject[i];
                    }
                }
            }
        }

        System.out.println("\n--- DỰ ÁN ĐANG CHẠY (RUNNING) VÀ GẦN KẾT THÚC NHẤT ---");
        if (targetProject == null) {
            System.out.println("⚠️ Thông báo: Hiện tại không có dự án nào có trạng thái RUNNING.");
        } else {
            targetProject.displayData();
        }
    }
}