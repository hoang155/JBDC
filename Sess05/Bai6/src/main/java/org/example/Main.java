import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] students = new String[100]; // Mảng chứa tối đa 100 sinh viên
        int count = 0; // Số lượng sinh viên hiện tại

        while (true) {
            System.out.println("\n****************** MENU ******************");
            System.out.println("1. Thêm tên sinh viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Tìm tên sinh viên chứa từ khóa");
            System.out.println("4. Đếm số sinh viên có tên bắt đầu bằng chữ cái");
            System.out.println("5. Sắp xếp danh sách tên theo thứ tự A-Z");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    if (count < students.length) {
                        System.out.print("Nhập tên sinh viên mới: ");
                        students[count] = sc.nextLine();
                        count++;
                        System.out.println("Thêm thành công!");
                    } else {
                        System.out.println("Danh sách đã đầy!");
                    }
                    break;

                case 2:
                    System.out.println("Danh sách sinh viên:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + students[i]);
                    }
                    break;

                case 3:
                    System.out.print("Nhập từ khóa cần tìm: ");
                    String keyword = sc.nextLine();
                    System.out.println("Kết quả tìm kiếm:");
                    for (int i = 0; i < count; i++) {
                        if (students[i].toLowerCase().contains(keyword.toLowerCase())) {
                            System.out.println("- " + students[i]);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Nhập chữ cái bắt đầu: ");
                    String prefix = sc.nextLine();
                    int counter = 0;
                    for (int i = 0; i < count; i++) {
                        if (students[i].toLowerCase().startsWith(prefix.toLowerCase())) {
                            counter++;
                            System.out.println("- Found: " + students[i]);
                        }
                    }
                    System.out.println("Tổng số: " + counter);
                    break;

                case 5:
                    // Chỉ sắp xếp phần mảng có chứa dữ liệu (từ 0 đến count)
                    if (count > 0) {
                        // Tạo mảng tạm để sắp xếp hoặc dùng sort trực tiếp trên đoạn có dữ liệu
                        Arrays.sort(students, 0, count);
                        System.out.println("Đã sắp xếp danh sách theo A-Z.");
                    }
                    break;

                case 6:
                    System.out.println("Kết thúc chương trình.");
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}