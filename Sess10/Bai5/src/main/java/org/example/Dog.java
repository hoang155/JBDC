package org.example;

public class Dog extends Mammal {
    public Dog(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof Woof!");
    }

    public void fetchBall() {
        System.out.println(name + " is fetching a ball!");
    }
}
