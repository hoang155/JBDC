package org.example;

import java.util.Scanner;
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập vận tốc (km/h): ");
        double velocity = scanner.nextDouble();

        System.out.print("Nhập thời gian đi (giờ): ");
        double time = scanner.nextDouble();

        double distance = velocity * time;

        System.out.printf("Quãng đường = %.0f (km)%n", distance);

        scanner.close();
    }
}
