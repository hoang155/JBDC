package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Car myCar = new Car();

        myCar.accelerate();
        myCar.printStatus();

        myCar.accelerate(20);
        myCar.printStatus();

        myCar.accelerate(10, 2);
        myCar.printStatus();
    }
}
