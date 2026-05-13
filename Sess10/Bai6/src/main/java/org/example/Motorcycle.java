package org.example;

public class Motorcycle extends MotorVehicle {
    public Motorcycle(String brand, int year, String fuelType) {
        super(brand, year, fuelType);
    }

    @Override
    public void startEngine() {
        System.out.println("Motorcycle engine starts: Pop Pop Bang!");
    }

    public void doWheelie() {
        System.out.println("The motorcycle is doing a wheelie!");
    }
}
