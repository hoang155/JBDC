package org.example;
import java.util.Scanner;

public class NetworkDevice extends Asset {
    private int numberOfPorts;

    public NetworkDevice() {
    }

    public NetworkDevice(String assetCode, String name, double purchasePrice, int numberOfPorts) {
        super(assetCode, name, purchasePrice);
        this.numberOfPorts = numberOfPorts;
    }

    // Overriding: Khấu hao 10% mỗi năm -> Giá trị còn lại = 90% giá gốc
    @Override
    public double getMarketValue() {
        return getPurchasePrice() * 0.9;
    }

    @Override
    public void inputData(Scanner scanner) {
        super.inputData(scanner); // Gọi hàm nhập của lớp cha

        while (true) {
            System.out.print("Nhập số lượng cổng mạng (Ports): ");
            try {
                this.numberOfPorts = Integer.parseInt(scanner.nextLine().trim());
                if (this.numberOfPorts <= 0) {
                    System.out.println("❌ Lỗi: Số lượng cổng phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Số lượng cổng mạng phải là một số nguyên!");
            }
        }
    }

    @Override
    public void displayData() {
        super.displayData();
        System.out.printf(" | Số Ports: %-3d | Giá sau khấu hao (Giảm 10%%): %,.2f VND (Thiết bị mạng)\n",
                numberOfPorts, getMarketValue());
    }
}
