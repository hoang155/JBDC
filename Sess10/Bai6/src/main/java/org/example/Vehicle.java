package org.example;

public class Vehicle {
    protected String brand;
    protected int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void showInfo() {
        System.out.print("Brand: " + brand + ", Year: " + year);
    }

    public void startEngine() {
        System.out.println("Vehicle engine starting...");
    }

    public void move() {
        System.out.println("Vehicle is moving.");
    }

    public void move(int speed) {
        System.out.println("Vehicle is moving at " + speed + " km/h.");
    }
}
