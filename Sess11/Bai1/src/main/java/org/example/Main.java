package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Shape[] shapes = new Shape[2];
        shapes[0] = new Rectangle("Rectangle 1", 5, 10);
        shapes[1] = new Circle("Circle 1", 7);

        for (Shape s : shapes) {
            s.displayInfo();
            System.out.printf("Diện tích: %.2f\n", s.getArea());
            System.out.printf("Chu vi: %.2f\n", s.getPerimeter());

            if (s instanceof Drawable) {
                ((Drawable) s).draw();
            }
            System.out.println("----------------------------");
        }
    }
}
