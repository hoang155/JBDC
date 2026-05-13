package org.example;

public class Car extends MotorVehicle {
    public Car(String brand, int year, String fuelType) {
        super(brand, year, fuelType);
    }

    @Override
    public void startEngine() {
        System.out.println("Car engine starts: Vroom Vroom!");
    }

    public void openTrunk() {
        System.out.println("The car's trunk is now open.");
    }
}
