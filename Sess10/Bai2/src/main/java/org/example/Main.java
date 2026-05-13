package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Shape s1 = new Rectangle(2, 3);
        Shape s2 = new Circle(1);

        System.out.println("Rectangle area: " + s1.area());
        System.out.println("Circle area: " + s2.area());
    }
}
