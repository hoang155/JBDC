    package org.example;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số thứ nhất (firstNumber): ");
        int firstNumber = scanner.nextInt();

        System.out.print("Nhập số thứ hai (secondNumber): ");
        int secondNumber = scanner.nextInt();

        int sum = firstNumber + secondNumber;
        int diff = firstNumber - secondNumber;
        int product = firstNumber * secondNumber;
        int quotient = firstNumber / secondNumber;
        int remainder = firstNumber % secondNumber;

        System.out.println("\n--- Kết quả ---");
        System.out.println("firstNumber = " + firstNumber);
        System.out.println("secondNumber = " + secondNumber);
        System.out.println("Tổng = " + sum);
        System.out.println("Hiệu = " + diff);
        System.out.println("Tích = " + product);
        System.out.println("Thương = " + quotient);
        System.out.println("Phần dư = " + remainder);

        scanner.close();
    }
}
