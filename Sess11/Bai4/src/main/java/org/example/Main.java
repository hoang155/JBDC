package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Device[] devices = new Device[3];
        devices[0] = new SmartPhone(1, "iPhone 15 Pro");
        devices[1] = new Laptop(2, "MacBook M3");
        devices[2] = new Television(3, "Sony Bravia 4K");

        System.out.println("===== HỆ THỐNG QUẢN LÝ THIẾT BỊ ĐIỆN TỬ =====");

        // 2. Duyệt danh sách thiết bị bằng vòng lặp
        for (Device dev : devices) {
            // Thực hiện bật thiết bị
            dev.turnOn();

            // Kiểm tra khả năng kết nối Wifi (Interface Connectable)
            if (dev instanceof Connectable) {
                Connectable connectableDev = (Connectable) dev; // Downcasting
                connectableDev.connectWifi();
            }

            // Kiểm tra khả năng sạc pin (Interface Chargeable)
            if (dev instanceof Chargeable) {
                Chargeable chargeableDev = (Chargeable) dev; // Downcasting
                chargeableDev.charge();
            }

            // Thực hiện tắt thiết bị
            dev.turnOff();
            System.out.println("---------------------------------------------");
        }
    }
}
