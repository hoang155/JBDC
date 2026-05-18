package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Asset> assetList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n============== HỆ THỐNG QUẢN LÝ TÀI SẢN TECHASSET ==============");
            System.out.println("1. Nhập tài sản thiết bị mới");
            System.out.println("2. Xuất báo cáo danh sách & Giá trị sau khấu hao");
            System.out.println("3. Tìm kiếm tài sản");
            System.out.println("4. Sửa đổi giá mua gốc của tài sản");
            System.out.println("5. Thoát");
            System.out.println("=================================================================");
            System.out.print("Lựa chọn chức năng của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addNewAsset(scanner);
                    break;
                case "2":
                    exportReport();
                    break;
                case "3":
                    searchMenu(scanner);
                    break;
                case "4":
                    updatePurchasePrice(scanner);
                    break;
                case "5":
                    System.out.println("👋 Đang đóng chương trình quản lý tài sản. Tạm biệt!");
                    System.exit(0);
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        }
    }

    // Yêu cầu Đa hình: In ra giá trị hiện tại của bất kỳ tài sản nào được truyền vào
    public static void showValue(Asset a) {
        System.out.printf("👉 Thiết bị [%s] có giá trị hiện tại sau khấu hao là: %,.2f VND\n",
                a.getName(), a.getMarketValue());
    }

    // Chức năng 1: Nhập tài sản mới
    private static void addNewAsset(Scanner scanner) {
        System.out.println("\nChọn loại thiết bị cần nhập:");
        System.out.println("1. Máy tính (Computer)");
        System.out.println("2. Thiết bị mạng (NetworkDevice)");
        System.out.print("Lựa chọn: ");
        String type = scanner.nextLine().trim();

        Asset asset = null;
        String prefix = "";
        if (type.equals("1")) {
            asset = new Computer();
            prefix = "COM";
        } else if (type.equals("2")) {
            asset = new NetworkDevice();
            prefix = "NET";
        } else {
            System.out.println("❌ Lỗi: Loại thiết bị chọn không hợp lệ!");
            return;
        }

        // Tạo mã tài sản tự động dựa theo vị trí để tránh trùng lặp
        String autoCode = prefix + String.format("%03d", assetList.size() + 1);
        asset.setAssetCode(autoCode);

        System.out.println("--- Đang thiết lập thông tin cho mã tài sản: " + autoCode + " ---");
        asset.inputData(scanner);

        assetList.add(asset);
        System.out.println("✅ Đã lưu tài sản mới thành công!");
    }

    // Chức năng 2: Xuất báo cáo danh sách tài sản
    private static void exportReport() {
        if (assetList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Danh sách quản lý tài sản đang trống.");
            return;
        }
        System.out.println("\n=========================== BÁO CÁO TÀI SẢN THIẾT BỊ ===========================");
        for (Asset asset : assetList) {
            asset.displayData();
            // Minh họa gọi hàm showValue đa hình theo yêu cầu đề bài
            showValue(asset);
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    // Chức năng 3: Menu chuyển hướng tìm kiếm (Phục vụ Overloading)
    private static void searchMenu(Scanner scanner) {
        if (assetList.isEmpty()) {
            System.out.println("⚠️ Thông báo: Hệ thống không có dữ liệu để tìm kiếm.");
            return;
        }
        System.out.println("\nChọn phương thức tìm kiếm:");
        System.out.println("1. Tìm kiếm đích danh theo Mã tài sản (assetCode)");
        System.out.println("2. Tìm kiếm các thiết bị có giá mua cao hơn một mức chỉ định (purchasePrice)");
        System.out.print("Lựa chọn: ");
        String searchChoice = scanner.nextLine().trim();

        if (searchChoice.equals("1")) {
            System.out.print("Nhập mã tài sản cần tìm (ví dụ COM001): ");
            String code = scanner.nextLine().trim();
            // Gọi hàm Overloading nhận tham số String
            searchAssets(code);
        } else if (searchChoice.equals("2")) {
            while (true) {
                System.out.print("Nhập mức giá mua sàn (VND): ");
                try {
                    double priceFloor = Double.parseDouble(scanner.nextLine().trim());
                    // Gọi hàm Overloading nhận tham số double
                    searchAssets(priceFloor);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Lỗi: Mức giá tìm kiếm phải là số thực!");
                }
            }
        } else {
            System.out.println("❌ Lỗi: Lựa chọn tìm kiếm sai quy cách!");
        }
    }

    // ================= KHU VỰC THỰC HIỆN OVERLOADING =================

    // Hàm tìm kiếm số 1: Nhận tham số kiểu String (Tìm kiếm theo Mã tài sản)
    public static void searchAssets(String assetCode) {
        boolean found = false;
        System.out.println("\n--- Kết quả tìm kiếm theo mã [" + assetCode + "] ---");
        for (Asset asset : assetList) {
            if (asset.getAssetCode().equalsIgnoreCase(assetCode)) {
                asset.displayData();
                found = true;
                break; // Vì mã tài sản là duy nhất nên có thể dừng vòng lặp ngay khi thấy
            }
        }
        if (!found) {
            System.out.println("⚠️ Không tìm thấy thiết bị nào trùng khớp với mã yêu cầu.");
        }
    }

    // Hàm tìm kiếm số 2: Nhận tham số kiểu double (Tìm kiếm thiết bị có giá lớn hơn mức chỉ định)
    public static void searchAssets(double purchasePriceFloor) {
        boolean found = false;
        System.out.printf("\n--- Danh sách thiết bị có giá gốc lớn hơn %,.2f VND ---\n", purchasePriceFloor);
        for (Asset asset : assetList) {
            if (asset.getPurchasePrice() > purchasePriceFloor) {
                asset.displayData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("⚠️ Không có thiết bị nào trong kho vượt qua mức giá sàn này.");
        }
    }
    // =================================================================

    // Chức năng 4: Sửa đổi giá gốc của tài sản
    private static void updatePurchasePrice(Scanner scanner) {
        System.out.print("Nhập mã tài sản bạn muốn cập nhật lại giá gốc: ");
        String id = scanner.nextLine().trim();
        Asset foundAsset = null;

        for (Asset asset : assetList) {
            if (asset.getAssetCode().equalsIgnoreCase(id)) {
                foundAsset = asset;
                break;
            }
        }

        if (foundAsset == null) {
            System.out.println("❌ Lỗi: Không tìm thấy tài sản nào ứng với mã vừa nhập.");
            return;
        }

        System.out.printf("Tài sản tìm thấy: %s (Giá gốc cũ: %,.2f VND)\n", foundAsset.getName(), foundAsset.getPurchasePrice());
        while (true) {
            System.out.print("Nhập mức giá gốc mới thay thế: ");
            try {
                double newPrice = Double.parseDouble(scanner.nextLine().trim());
                if (newPrice <= 0) {
                    System.out.println("❌ Lỗi: Giá mua mới bắt buộc phải lớn hơn 0!");
                    continue;
                }
                foundAsset.setPurchasePrice(newPrice);
                System.out.println("✅ Đã cập nhật thành công giá gốc mới cho tài sản.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Định dạng tiền nhập vào không hợp lệ!");
            }
        }
    }
}
