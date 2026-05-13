package org.example;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        Dog dog = new Dog("Buddy", 3, true);
        Cat cat = new Cat("Kitty", 2, true);
        Elaphant elaphant = new Elaphant("Dumbo", 10, false);

        Animal[] zoo = {dog, cat, elaphant};
        int choice;
        do {
            System.out.println("\n================ ZOO MANAGEMENT MENU ================");
            System.out.println("1. Tạo đối tượng và hiển thị thông tin (Kế thừa + super)");
            System.out.println("2. Kiểm tra Overriding: gọi makeSound() của từng con vật");
            System.out.println("3. Kiểm tra Overloading: gọi eat() và eat(String)");
            System.out.println("4. Kiểm tra đa hình runtime (Animal array)");
            System.out.println("5. Gọi phương thức đặc trưng của từng loài");
            System.out.println("0. Thoát chương trình");
            System.out.println("=====================================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    dog.showInfo();
                    cat.showInfo();
                    elaphant.showInfo();
                    break;
                case 2:
                    dog.makeSound();
                    cat.makeSound();
                    elaphant.makeSound();
                    break;
                case 3:
                    dog.eat();
                    dog.eat("Bone");
                    break;
                case 4:
                    System.out.println("--- Runtime Polymorphism ---");
                    for (Animal a : zoo) {
                        a.makeSound(); // Java tự quyết định gọi hàm của lớp con tương ứng
                    }
                    break;
                case 5:
                    dog.fetchBall();
                    cat.climbTree();
                    elaphant.sprayWater();
                    break;
                case 0:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
        sc.close();
    }
}
