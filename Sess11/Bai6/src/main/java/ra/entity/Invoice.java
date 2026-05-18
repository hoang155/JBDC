package ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Invoice {
    private String invoiceId;
    private String customerName;
    private LocalDate invoiceDate;
    private InvoiceDetail[] invoiceDetails = new InvoiceDetail[0]; // Mảng động lưu chi tiết hóa đơn
    private double totalAmount;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Invoice() {
    }

    public void inputData(Scanner scanner, Product[] arrProd, int prodIndex) {
        // 1. Nhập và validate Invoice ID
        while (true) {
            System.out.print("Nhập mã hóa đơn (HDxxxx - đúng 6 ký tự): ");
            String idInput = scanner.nextLine().trim();
            if (!idInput.matches("^HD\\w{4}$") || idInput.length() != 6) {
                System.out.println("❌ Lỗi: Mã hóa đơn phải bắt đầu bằng chữ HD và có đúng 6 ký tự!");
                continue;
            }
            this.invoiceId = idInput;
            break;
        }

        // 2. Nhập tên khách hàng
        while (true) {
            System.out.print("Nhập tên khách hàng: ");
            String custName = scanner.nextLine().trim();
            if (custName.isEmpty()) {
                System.out.println("❌ Lỗi: Tên khách hàng không được để trống!");
                continue;
            }
            this.customerName = custName;
            break;
        }

        // 3. Nhập ngày lập hóa đơn
        while (true) {
            System.out.print("Nhập ngày lập hóa đơn (dd/MM/yyyy): ");
            try {
                this.invoiceDate = LocalDate.parse(scanner.nextLine().trim(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Định dạng ngày không hợp lệ (ví dụ đúng: 18/05/2026)!");
            }
        }

        // 4. Nhập danh sách sản phẩm mua vào chi tiết hóa đơn
        while (true) {
            InvoiceDetail detail = new InvoiceDetail();
            detail.inputData(scanner, arrProd, prodIndex);

            // Thêm chi tiết vừa nhập vào mảng động
            InvoiceDetail[] newArray = new InvoiceDetail[this.invoiceDetails.length + 1];
            System.arraycopy(this.invoiceDetails, 0, newArray, 0, this.invoiceDetails.length);
            newArray[newArray.length - 1] = detail;
            this.invoiceDetails = newArray;

            System.out.print("Bạn có muốn mua tiếp sản phẩm khác cho hóa đơn này không? (Y/N): ");
            String next = scanner.nextLine().trim();
            if (!next.equalsIgnoreCase("Y")) {
                break;
            }
        }

        // 5. Tính toán tổng tiền hóa đơn
        calculateTotalAmount();
    }

    public void calculateTotalAmount() {
        this.totalAmount = 0;
        for (InvoiceDetail detail : invoiceDetails) {
            this.totalAmount += detail.getSubTotal();
        }
    }

    public void displayData() {
        System.out.printf("Mã HĐ: %-8s | Khách hàng: %-20s | Ngày lập: %s | TỔNG TIỀN: %,.2f VND\n",
                invoiceId, customerName, invoiceDate.format(formatter), totalAmount);
        System.out.println("   Chi tiết mặt hàng đã mua:");
        for (InvoiceDetail detail : invoiceDetails) {
            detail.displayData();
        }
    }

    // Getters và Setters
    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public InvoiceDetail[] getInvoiceDetails() { return invoiceDetails; }
    public void setInvoiceDetails(InvoiceDetail[] invoiceDetails) { this.invoiceDetails = invoiceDetails; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}