package ra.presentation;

import ra.entity.Invoice;
import ra.entity.InvoiceDetail;
import ra.entity.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InvoiceManagement {
    private static final Product[] arrProduct = new Product[100];
    private static int productIndex = 0;

    private static final Invoice[] arrInvoice = new Invoice[100];
    private static int invoiceIndex = 0;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==================== QUẢN LÝ HÓA ĐƠN ====================");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý hóa đơn");
            System.out.println("3. Báo cáo doanh thu");
            System.out.println("4. Thoát");
            System.out.println("=========================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayProductMenu(scanner);
                    break;
                case "2":
                    displayInvoiceMenu(scanner);
                    break;
                case "3":
                    displayReportMenu(scanner);
                    break;
                case "4":
                    System.out.println("👋 Tạm biệt và hẹn gặp lại!");
                    System.exit(0);
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ!");
            }
        }
    }

    // ================= MENU 1: QUẢN LÝ SẢN PHẨM =================
    private static void displayProductMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================== QUẢN LÝ SẢN PHẨM ====================");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm (nếu chưa có trong hóa đơn nào)");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Thoát");
            System.out.println("==========================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    if (productIndex >= arrProduct.length) {
                        System.out.println("❌ Lỗi: Kho bộ nhớ lưu trữ sản phẩm đầy!");
                        break;
                    }
                    Product p = new Product();
                    p.inputData(scanner, arrProduct, productIndex);
                    arrProduct[productIndex++] = p;
                    System.out.println("✅ Thêm sản phẩm thành công!");
                    break;
                case "2":
                    if (productIndex == 0) {
                        System.out.println("⚠️ Thông báo: Không có sản phẩm nào.");
                    } else {
                        System.out.println("\n--- KHO DANH SÁCH SẢN PHẨM ---");
                        for (int i = 0; i < productIndex; i++) {
                            arrProduct[i].displayData();
                        }
                    }
                    break;
                case "3":
                    updateProduct(scanner);
                    break;
                case "4":
                    deleteProduct(scanner);
                    break;
                case "5":
                    searchProductByName(scanner);
                    break;
                case "6":
                    return;
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần sửa đổi: ");
        String id = scanner.nextLine().trim();
        int foundIdx = -1;
        for (int i = 0; i < productIndex; i++) {
            if (arrProduct[i].getProductId().equalsIgnoreCase(id)) {
                foundIdx = i;
                break;
            }
        }
        if (foundIdx == -1) {
            System.out.println("❌ Lỗi: Không tìm thấy sản phẩm!");
            return;
        }

        Product[] temp = new Product[productIndex - 1];
        int k = 0;
        for (int i = 0; i < productIndex; i++) {
            if (i != foundIdx) temp[k++] = arrProduct[i];
        }

        System.out.println("--- Nhập thông tin mới cho sản phẩm " + id + " ---");
        Product updatedProd = new Product();
        updatedProd.inputData(scanner, temp, temp.length);
        arrProduct[foundIdx] = updatedProd;
        System.out.println("✅ Cập nhật sản phẩm thành công!");
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String id = scanner.nextLine().trim();
        int foundIdx = -1;
        for (int i = 0; i < productIndex; i++) {
            if (arrProduct[i].getProductId().equalsIgnoreCase(id)) {
                foundIdx = i;
                break;
            }
        }
        if (foundIdx == -1) {
            System.out.println("❌ Lỗi: Không tồn tại sản phẩm!");
            return;
        }

        // Kiểm tra ràng buộc: sản phẩm đã có trong hóa đơn nào chưa
        boolean isUsed = false;
        for (int i = 0; i < invoiceIndex; i++) {
            for (InvoiceDetail detail : arrInvoice[i].getInvoiceDetails()) {
                if (detail.getProduct().getProductId().equalsIgnoreCase(id)) {
                    isUsed = true;
                    break;
                }
            }
        }

        if (isUsed) {
            System.out.println("❌ Lỗi: Sản phẩm đã tồn tại trong hóa đơn bán hàng, không được phép xóa!");
            return;
        }

        for (int i = foundIdx; i < productIndex - 1; i++) {
            arrProduct[i] = arrProduct[i + 1];
        }
        arrProduct[--productIndex] = null;
        System.out.println("✅ Đã xóa sản phẩm khỏi hệ thống.");
    }

    private static void searchProductByName(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        for (int i = 0; i < productIndex; i++) {
            if (arrProduct[i].getProductName().toLowerCase().contains(keyword)) {
                arrProduct[i].displayData();
                found = true;
            }
        }
        if (!found) System.out.println("⚠️ Không tìm thấy sản phẩm tương ứng.");
    }

    // ================= MENU 2: QUẢN LÝ HÓA ĐƠN =================
    private static void displayInvoiceMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================== QUẢN LÝ HÓA ĐƠN ====================");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Hiển thị danh sách hóa đơn");
            System.out.println("3. Cập nhật thông tin hóa đơn");
            System.out.println("4. Xóa hóa đơn");
            System.out.println("5. Tìm hóa đơn theo mã");
            System.out.println("6. Tìm hóa đơn theo tên khách hàng");
            System.out.println("7. Thoát");
            System.out.println("=========================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    if (productIndex == 0) {
                        System.out.println("❌ Lỗi: Phải thêm sản phẩm vào hệ thống trước khi tạo hóa đơn!");
                        break;
                    }
                    Invoice inv = new Invoice();
                    inv.inputData(scanner, arrProduct, productIndex);
                    arrInvoice[invoiceIndex++] = inv;
                    System.out.println("✅ Thêm hóa đơn thành công!");
                    break;
                case "2":
                    if (invoiceIndex == 0) {
                        System.out.println("⚠️ Thông báo: Danh sách hóa đơn đang trống.");
                    } else {
                        System.out.println("\n--- DANH SÁCH CÁC HÓA ĐƠN ---");
                        for (int i = 0; i < invoiceIndex; i++) {
                            arrInvoice[i].displayData();
                            System.out.println("---------------------------------------------------------");
                        }
                    }
                    break;
                case "3":
                    updateInvoice(scanner);
                    break;
                case "4":
                    deleteInvoice(scanner);
                    break;
                case "5":
                    System.out.print("Nhập mã hóa đơn cần tìm: ");
                    String id = scanner.nextLine().trim();
                    boolean fId = false;
                    for (int i = 0; i < invoiceIndex; i++) {
                        if (arrInvoice[i].getInvoiceId().equalsIgnoreCase(id)) {
                            arrInvoice[i].displayData();
                            fId = true;
                            break;
                        }
                    }
                    if (!fId) System.out.println("⚠️ Không tìm thấy hóa đơn có mã " + id);
                    break;
                case "6":
                    System.out.print("Nhập tên khách hàng cần tìm hóa đơn: ");
                    String name = scanner.nextLine().trim().toLowerCase();
                    boolean fName = false;
                    for (int i = 0; i < invoiceIndex; i++) {
                        if (arrInvoice[i].getCustomerName().toLowerCase().contains(name)) {
                            arrInvoice[i].displayData();
                            fName = true;
                        }
                    }
                    if (!fName) System.out.println("⚠️ Không tìm thấy hóa đơn nào của khách hàng này.");
                    break;
                case "7":
                    return;
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void updateInvoice(Scanner scanner) {
        System.out.print("Nhập mã hóa đơn cần cập nhật: ");
        String id = scanner.nextLine().trim();
        int idx = -1;
        for (int i = 0; i < invoiceIndex; i++) {
            if (arrInvoice[i].getInvoiceId().equalsIgnoreCase(id)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("❌ Lỗi: Không tìm thấy hóa đơn.");
            return;
        }
        System.out.println("--- Tiến hành nhập lại thông tin mới cho hóa đơn " + id + " ---");
        Invoice updatedInv = new Invoice();
        updatedInv.inputData(scanner, arrProduct, productIndex);
        arrInvoice[idx] = updatedInv;
        System.out.println("✅ Cập nhật hóa đơn thành công!");
    }

    private static void deleteInvoice(Scanner scanner) {
        System.out.print("Nhập mã hóa đơn cần xóa: ");
        String id = scanner.nextLine().trim();
        int idx = -1;
        for (int i = 0; i < invoiceIndex; i++) {
            if (arrInvoice[i].getInvoiceId().equalsIgnoreCase(id)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("❌ Lỗi: Hóa đơn không tồn tại!");
            return;
        }
        for (int i = idx; i < invoiceIndex - 1; i++) {
            arrInvoice[i] = arrInvoice[i + 1];
        }
        arrInvoice[--invoiceIndex] = null;
        System.out.println("✅ Đã xóa hóa đơn thành công.");
    }

    // ================= MENU 3: BÁO CÁO DOANH THU =================
    private static void displayReportMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================== QUẢN LÝ DOANH THU ====================");
            System.out.println("1. Tính tổng doanh thu tất cả cả hóa đơn");
            System.out.println("2. Tìm hóa đơn có giá trị lớn nhất");
            System.out.println("3. Thống kê số hóa đơn theo khoảng ngày (nhập từ – đến)");
            System.out.println("4. Thống kê tổng doanh thu theo khoảng ngày");
            System.out.println("5. Thoát");
            System.out.println("===========================================================");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    double total = 0;
                    for (int i = 0; i < invoiceIndex; i++) total += arrInvoice[i].getTotalAmount();
                    System.out.printf("💰 Tổng doanh thu của tất cả hóa đơn: %,.2f VND\n", total);
                    break;
                case "2":
                    if (invoiceIndex == 0) {
                        System.out.println("⚠️ Thông báo: Hệ thống chưa có hóa đơn nào.");
                        break;
                    }
                    Invoice maxInv = arrInvoice[0];
                    for (int i = 1; i < invoiceIndex; i++) {
                        if (arrInvoice[i].getTotalAmount() > maxInv.getTotalAmount()) {
                            maxInv = arrInvoice[i];
                        }
                    }
                    System.out.println("\n🌟 HÓA ĐƠN CÓ GIÁ TRỊ LỚN NHẤT:");
                    maxInv.displayData();
                    break;
                case "3":
                    reportInvoiceCountByDateRange(scanner);
                    break;
                case "4":
                    reportRevenueByDateRange(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void reportInvoiceCountByDateRange(Scanner scanner) {
        try {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            LocalDate start = LocalDate.parse(scanner.nextLine().trim(), formatter);
            System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
            LocalDate end = LocalDate.parse(scanner.nextLine().trim(), formatter);

            int count = 0;
            System.out.println("\n--- Các hóa đơn trong khoảng ngày đã nhập ---");
            for (int i = 0; i < invoiceIndex; i++) {
                LocalDate date = arrInvoice[i].getInvoiceDate();
                if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))) {
                    arrInvoice[i].displayData();
                    count++;
                }
            }
            System.out.println("📊 Tổng số lượng hóa đơn tìm thấy: " + count);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Lỗi: Định dạng ngày tháng nhập vào không chính xác!");
        }
    }

    private static void reportRevenueByDateRange(Scanner scanner) {
        try {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            LocalDate start = LocalDate.parse(scanner.nextLine().trim(), formatter);
            System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
            LocalDate end = LocalDate.parse(scanner.nextLine().trim(), formatter);

            double revenue = 0;
            for (int i = 0; i < invoiceIndex; i++) {
                LocalDate date = arrInvoice[i].getInvoiceDate();
                if ((date.isAfter(start) || date.isEqual(start)) && (date.isBefore(end) || date.isEqual(end))) {
                    revenue += arrInvoice[i].getTotalAmount();
                }
            }
            System.out.printf("💰 Tổng doanh thu thống kê từ %s đến %s là: %,.2f VND\n",
                    start.format(formatter), end.format(formatter), revenue);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Lỗi: Định dạng ngày tháng không hợp lệ!");
        }
    }
}