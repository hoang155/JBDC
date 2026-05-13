package org.example;

public class Cat extends Mammal {
    public Cat(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow Meow!");
    }

    public void climbTree() {
        System.out.println(name + " is climbing a tree!");
    }
}
