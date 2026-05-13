package org.example;

public class Truck extends MotorVehicle {
    public Truck(String brand, int year, String fuelType) {
        super(brand, year, fuelType);
    }
    @Override
    public void startEngine() {
        System.out.println("Truck engine starts: Rrrrrumble!");
    }

    public void loadCargo() {
        System.out.println("The truck is loading heavy cargo.");
    }
}
