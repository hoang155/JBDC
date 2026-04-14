package CircleArea;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập bán kính r: ");
        double radius = scanner.nextDouble();
        double area = Math.PI * radius * radius;
        System.out.printf("Diện tích : %.2f%n", area);

        scanner.close();
    }
}
