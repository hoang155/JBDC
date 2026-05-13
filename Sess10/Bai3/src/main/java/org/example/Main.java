package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Computer myComputer = new Computer();

        System.out.println("Final Price = " + myComputer.calculatePrice(1000.0) + "\n");

        System.out.println("Final Price = " + myComputer.calculatePrice(1000.0, 100.0) + "\n");

        System.out.println("Final Price = " + myComputer.calculatePrice(1000.0, 100.0, 50.0));
    }
}
