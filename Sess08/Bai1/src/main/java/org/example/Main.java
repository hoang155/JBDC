package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Student student1 = new Student("Alice", 20, 1);
        System.out.println("ID: " + student1.getId() + ", Name: " + student1.getName() + ", Age: " + student1.getAge());
    }
}
