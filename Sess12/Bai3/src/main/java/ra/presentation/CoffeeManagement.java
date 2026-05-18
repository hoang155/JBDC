package ra.presentation;

import ra.entity.Coffee;
import ra.entity.Drink;
import ra.entity.FruitTea;

import java.util.ArrayList;
import java.util.Scanner;

public class CoffeeManagement {
    // Sử dụng ArrayList giúp quản lý danh sách động, xóa phần tử không ảnh hưởng đến vị trí khác
    private static final ArrayList<Drink> menuList = new ArrayList<>();
    private static int autoIncrementId = 1; // Tự động tăng mã ID cho món ăn tránh trùng lặp

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==================== QUẢN LÝ QUÁN CÀ PHÊ ====================");
            System.out.println("1. Thêm món vào Menu");
            System.out.println("2. Hiển thị Menu");
            System.out.println("3. Áp dụng mã giảm giá (%)");
            System.out.println("4. Xóa món khỏi Menu");
            System.out.println("5. Thống kê giá tiền trung bình");
            System.out.println("6. Thoát");
            System.out.println("=============================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addNewDrink(scanner);
                    break;
                case "2":
                    displayMenu();
                    break;
                case "3":
                    applyDiscountToMenu(scanner);
                    break;
                case "4":
                    deleteDrinkFromMenu(scanner);
                    break;
                case "5":
                    calculateAveragePrice();
                    break;
                case "6":
                    System.out.println("👋 Đang đóng phần mềm quản lý tính tiền. Tạm biệt!");
                    System.exit(0);
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ! Vui lòng chọn từ 1 đến 6.");
            }
        }
    }

    // Chức năng 1: Thêm món mới vào Menu
    private static void addNewDrink(Scanner scanner) {
        System.out.println("\nChọn loại đồ uống cần thêm:");
        System.out.println("1. Cà phê (Coffee)");
        System.out.println("2. Trà trái cây (FruitTea)");
        System.out.print("Lựa chọn: ");
        String type = scanner.nextLine().trim();

        Drink drink = null;
        if (type.equals("1")) {
            drink = new Coffee();
        } else if (type.equals("2")) {
            drink = new FruitTea();
        } else {
            System.out.println("❌ Lỗi: Kiểu chọn loại đồ uống không đúng mẫu!");
            return;
        }

        // Thiết lập ID tự động và nhập dữ liệu
        drink.setId(autoIncrementId++);
        drink.inputData(scanner);

        menuList.add(drink);
        System.out.println("✅ Đã thêm món mới vào danh mục menu thành công!");
    }

    // Chức năng 2: Hiển thị danh sách món ăn (Tính đa hình tại runtime)
    private static void displayMenu() {
        if (menuList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Hiện tại thực đơn menu của quán đang trống.");
            return;
        }
        System.out.println("\n======================== MENU ĐỒ UỐNG ========================");
        for (Drink d : menuList) {
            d.displayData(); // Tính đa hình: Tự biết món nào là Coffee hay FruitTea để gọi prepare() thích hợp
        }
        System.out.println("==============================================================");
    }

    // Chức năng 3: Áp dụng mã giảm giá qua Interface IPromotion
    private static void applyDiscountToMenu(Scanner scanner) {
        if (menuList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Menu đang trống, không thể áp dụng mã giảm giá.");
            return;
        }

        double percent = 0;
        while (true) {
            System.out.print("Nhập phần trăm muốn giảm giá (0 - 100%): ");
            try {
                percent = Double.parseDouble(scanner.nextLine().trim());
                if (percent < 0 || percent > 100) {
                    System.out.println("❌ Lỗi: Tỷ lệ phần trăm giảm giá phải nằm từ khoảng 0 đến 100!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Phần trăm giảm giá phải nhập định dạng số thực!");
            }
        }

        // Duyệt toàn bộ danh sách và áp dụng phương thức interface
        for (Drink d : menuList) {
            d.applyDiscount(percent);
        }
        System.out.printf("✅ Đã áp dụng giảm giá giảm đồng loạt %,.1f%% cho tất cả các món trong Menu!\n", percent);
    }

    // Chức năng 4: Xóa món khỏi danh mục theo mã ID
    private static void deleteDrinkFromMenu(Scanner scanner) {
        if (menuList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh sách thực đơn trống, không có gì để xóa.");
            return;
        }

        System.out.print("Nhập mã ID đồ uống bạn muốn xóa: ");
        try {
            int deleteId = Integer.parseInt(scanner.nextLine().trim());
            int foundIndex = -1;

            for (int i = 0; i < menuList.size(); i++) {
                if (menuList.get(i).getId() == deleteId) {
                    foundIndex = i;
                    break;
                }
            }

            if (foundIndex == -1) {
                System.out.println("❌ Lỗi: Không tìm thấy món ăn nào ứng với mã ID vừa nhập.");
            } else {
                Drink removedDrink = menuList.remove(foundIndex); // ArrayList tự động dồn dịch các phần tử còn lại phía sau
                System.out.println("✅ Xóa thành công món: " + removedDrink.getName() + " [ID: " + deleteId + "] khỏi Menu.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: Mã ID đồ uống tìm kiếm bắt buộc phải là số nguyên!");
        }
    }

    // Chức năng 5: Thống kê tính tổng giá tiền trung bình
    private static void calculateAveragePrice() {
        if (menuList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Hiện tại chưa có món ăn nào để tính toán giá trị trung bình.");
            return;
        }

        double totalPriceSum = 0;
        for (Drink d : menuList) {
            totalPriceSum += d.getPrice();
        }
        double avgPrice = totalPriceSum / menuList.size();

        System.out.printf("📊 Tổng số lượng món hiện tại: %d món\n", menuList.size());
        System.out.printf("💰 Giá bán trung bình của các đồ uống trong Menu: %,.2f VND\n", avgPrice);
    }
}