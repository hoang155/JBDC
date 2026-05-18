package ra.entity;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private double price;
    public enum ProductStatus {
        AVAILABLE,
        OUT_OF_STOCK,
        STOP_SELLING
    }
    private ProductStatus status;

    public Product() {
    }

    public Product(String productId, String productName, double price, ProductStatus status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProd, int index) {
        // 1. Nhập và validate Product ID
        while (true) {
            System.out.print("Nhập mã sản phẩm (Cxxx, Sxxx, Axxx - đúng 4 ký tự): ");
            String idInput = scanner.nextLine().trim();
            if (!idInput.matches("^[CSA]\\d{3}$")) {
                System.out.println("❌ Lỗi: Mã sản phẩm phải bắt đầu bằng C, S hoặc A và có đúng 4 ký tự!");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < index; i++) {
                if (arrProd[i].getProductId().equalsIgnoreCase(idInput)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("❌ Lỗi: Mã sản phẩm này đã tồn tại!");
                continue;
            }
            this.productId = idInput;
            break;
        }

        // 2. Nhập và validate Product Name
        while (true) {
            System.out.print("Nhập tên sản phẩm (10-50 ký tự, duy nhất): ");
            String nameInput = scanner.nextLine().trim();
            if (nameInput.length() < 10 || nameInput.length() > 50) {
                System.out.println("❌ Lỗi: Tên sản phẩm phải có độ dài từ 10 đến 50 ký tự!");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < index; i++) {
                if (arrProd[i].getProductName().equalsIgnoreCase(nameInput)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("❌ Lỗi: Tên sản phẩm này đã tồn tại!");
                continue;
            }
            this.productName = nameInput;
            break;
        }

        // 3. Nhập và validate Price
        while (true) {
            System.out.print("Nhập giá bán (> 0): ");
            try {
                double priceInput = Double.parseDouble(scanner.nextLine().trim());
                if (priceInput <= 0) {
                    System.out.println("❌ Lỗi: Giá bán phải lớn hơn 0!");
                    continue;
                }
                this.price = priceInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Giá bán phải là một số thực hợp lệ!");
            }
        }

        // 4. Nhập trạng thái sản phẩm
        while (true) {
            System.out.print("Chọn trạng thái (1. AVAILABLE, 2. OUT_OF_STOCK, 3. STOP_SELLING): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": this.status = ProductStatus.AVAILABLE; break;
                case "2": this.status = ProductStatus.OUT_OF_STOCK; break;
                case "3": this.status = ProductStatus.STOP_SELLING; break;
                default:
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ!");
                    continue;
            }
            break;
        }
    }

    public void displayData() {
        System.out.printf("Mã SP: %-6s | Tên SP: %-25s | Giá: %,.2f VND | Trạng thái: %-12s\n",
                productId, productName, price, status);
    }
}
