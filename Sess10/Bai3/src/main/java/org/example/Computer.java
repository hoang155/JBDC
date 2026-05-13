package org.example;

public class Computer {
    private double basePrice;
    private double tax;
    private double discount;

    public Computer() {
    }

    public Computer(double basePrice, double tax, double discount) {
        this.basePrice = basePrice;
        this.tax = tax;
        this.discount = discount;
    }

    public double calculatePrice(double basePrice) {
        System.out.println("[Using basePrice only]");
        return basePrice;
    }

    public double calculatePrice(double basePrice, double tax) {
        System.out.println("[Using basePrice + tax]");
        return basePrice + tax;
    }

    public double calculatePrice(double basePrice, double tax, double discount) {
        System.out.println("[Using basePrice + tax + discount]");
        return basePrice + tax - discount;
    }
}
