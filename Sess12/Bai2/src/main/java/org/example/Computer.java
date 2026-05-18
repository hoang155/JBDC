package org.example;
import java.util.Scanner;

public class Computer extends Asset {
    private int ram;
    private String cpu;

    public Computer() {
    }

    public Computer(String assetCode, String name, double purchasePrice, int ram, String cpu) {
        super(assetCode, name, purchasePrice);
        this.ram = ram;
        this.cpu = cpu;
    }

    // Overriding: Khấu hao 20% mỗi năm -> Giá trị còn lại = 80% giá gốc
    @Override
    public double getMarketValue() {
        return getPurchasePrice() * 0.8;
    }

    @Override
    public void inputData(Scanner scanner) {
        super.inputData(scanner); // Gọi hàm nhập của lớp cha

        while (true) {
            System.out.print("Nhập dung lượng RAM (GB): ");
            try {
                this.ram = Integer.parseInt(scanner.nextLine().trim());
                if (this.ram <= 0) {
                    System.out.println("❌ Lỗi: Dung lượng RAM phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: RAM phải là một số nguyên!");
            }
        }

        System.out.print("Nhập loại CPU: ");
        this.cpu = scanner.nextLine().trim();
    }

    @Override
    public void displayData() {
        super.displayData();
        System.out.printf(" | RAM: %d GB | CPU: %-6s | Giá sau khấu hao (Giảm 20%%): %,.2f VND (Máy tính)\n",
                ram, cpu, getMarketValue());
    }
}
