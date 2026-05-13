package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        vehicleList.add(new Car("Toyota", 2022, "Xăng"));
        vehicleList.add(new Motorcycle("Honda", 2021, "Xăng"));
        vehicleList.add(new Truck("Volvo", 2020, "Dầu"));

        int choice;
        do {
            System.out.println("\n================ VEHICLE MANAGEMENT MENU ================");
            System.out.println("1. Hiển thị thông tin tất cả phương tiện");
            System.out.println("2. Kiểm tra Overriding: startEngine()");
            System.out.println("3. Kiểm tra Overloading: move()");
            System.out.println("4. Kiểm tra đa hình runtime (mảng Vehicle[])");
            System.out.println("5. Gọi các hành vi đặc trưng theo loại");
            System.out.println("6. Thêm phương tiện mới (Car/Motorcycle/Truck)");
            System.out.println("0. Thoát");
            System.out.println("=========================================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    for (Vehicle v : vehicleList) v.showInfo();
                    break;
                case 2:
                    for (Vehicle v : vehicleList) v.startEngine();
                    break;
                case 3:
                    if (!vehicleList.isEmpty()) {
                        vehicleList.get(0).move();
                        vehicleList.get(0).move(80);
                    }
                    break;
                case 4:
                    System.out.println("--- Đa hình Runtime ---");
                    for (Vehicle v : vehicleList) {
                        v.startEngine();
                    }
                    break;
                case 5:
                    for (Vehicle v : vehicleList) {
                        if (v instanceof Car) ((Car) v).openTrunk();
                        else if (v instanceof Motorcycle) ((Motorcycle) v).doWheelie();
                        else if (v instanceof Truck) ((Truck) v).loadCargo();
                    }
                    break;
                case 6:
                    System.out.print("Loại xe (1-Car, 2-Motor, 3-Truck): ");
                    int type = sc.nextInt(); sc.nextLine();
                    System.out.print("Brand: "); String b = sc.nextLine();
                    System.out.print("Year: "); int y = sc.nextInt(); sc.nextLine();
                    System.out.print("Fuel Type: "); String f = sc.nextLine();

                    if (type == 1) vehicleList.add(new Car(b, y, f));
                    else if (type == 2) vehicleList.add(new Motorcycle(b, y, f));
                    else if (type == 3) vehicleList.add(new Truck(b, y, f));
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
        sc.close();
    }
}
