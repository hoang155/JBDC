package org.example;

public class Elaphant extends Mammal {
    public Elephant(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Pawoo!");
    }

    public void sprayWater() {
        System.out.println(name + " is spraying water!");
    }
}
