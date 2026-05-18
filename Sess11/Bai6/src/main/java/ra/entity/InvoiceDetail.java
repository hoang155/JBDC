package ra.entity;

import java.util.Scanner;

public class InvoiceDetail {
    private Product product;
    private int quantity;
    private double subTotal;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subTotal = product.getPrice() * quantity;
    }

    public void inputData(Scanner scanner, Product[] arrProd, int prodIndex) {
        System.out.println("\n--- DANH SÁCH SẢN PHẨM SẴN CÓ ---");
        for (int i = 0; i < prodIndex; i++) {
            System.out.print("[" + (i + 1) + "] ");
            arrProd[i].displayData();
        }

        // 1. Chọn sản phẩm từ danh sách
        while (true) {
            System.out.print("Nhập mã sản phẩm để mua: ");
            String pId = scanner.nextLine().trim();
            Product foundProd = null;
            for (int i = 0; i < prodIndex; i++) {
                if (arrProd[i].getProductId().equalsIgnoreCase(pId)) {
                    foundProd = arrProd[i];
                    break;
                }
            }

            if (foundProd == null) {
                System.out.println("❌ Lỗi: Không tìm thấy sản phẩm có mã " + pId);
                continue;
            }

            if (foundProd.getStatus() == Product.ProductStatus.STOP_SELLING) {
                System.out.println("❌ Lỗi: Sản phẩm này đã dừng bán, vui lòng chọn sản phẩm khác!");
                continue;
            }

            this.product = foundProd;
            break;
        }

        // 2. Nhập và validate số lượng mua
        while (true) {
            System.out.print("Nhập số lượng mua (> 0): ");
            try {
                int qtyInput = Integer.parseInt(scanner.nextLine().trim());
                if (qtyInput <= 0) {
                    System.out.println("❌ Lỗi: Số lượng mua phải lớn hơn 0!");
                    continue;
                }
                this.quantity = qtyInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Số lượng phải là số nguyên hợp lệ!");
            }
        }

        // 3. Tự động tính thành tiền
        this.subTotal = this.product.getPrice() * this.quantity;
    }

    public void displayData() {
        System.out.printf("   + Tên SP: %-20s | Đơn giá: %,.2f VND | Số lượng: %-4d | Thành tiền: %,.2f VND\n",
                product.getProductName(), product.getPrice(), quantity, subTotal);
    }

    // Getters và Setters
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getSubTotal() { return subTotal; }
    public void setSubTotal(double subTotal) { this.subTotal = subTotal; }
}