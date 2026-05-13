package org.example;

public class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void showInfo() {
        System.out.print("Name: " + name + ", Age: " + age);
    }

    public void makeSound() {
        System.out.println("Animal makes a sound");
    }

    // Overloading eat()
    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void eat(String food) {
        System.out.println(name + " is eating " + food + ".");
    }
}
