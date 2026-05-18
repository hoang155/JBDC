package org.example;

import java.util.Scanner;

public abstract class Asset {
    private String assetCode;
    private String name;
    private double purchasePrice;

    public Asset() {
    }

    public Asset(String assetCode, String name, double purchasePrice) {
        this.assetCode = assetCode;
        this.name = name;
        this.purchasePrice = purchasePrice;
    }

    // Phương thức trừu tượng tính giá trị thị trường sau khấu hao
    public abstract double getMarketValue();

    // Nhập thông tin chung cho tài sản
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên tài sản: ");
        this.name = scanner.nextLine().trim();

        while (true) {
            System.out.print("Nhập giá mua gốc (VND): ");
            try {
                this.purchasePrice = Double.parseDouble(scanner.nextLine().trim());
                if (this.purchasePrice <= 0) {
                    System.out.println("❌ Lỗi: Giá mua phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Giá mua phải là số thực hợp lệ!");
            }
        }
    }

    // Hiển thị thông tin chung
    public void displayData() {
        System.out.printf("Mã TS: %-8s | Tên TS: %-15s | Giá mua gốc: %,.2f VND", assetCode, name, purchasePrice);
    }

    // Getters và Setters
    public String getAssetCode() { return assetCode; }
    public void setAssetCode(String assetCode) { this.assetCode = assetCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }
}