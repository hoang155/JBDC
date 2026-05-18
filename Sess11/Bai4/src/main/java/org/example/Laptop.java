package org.example;

public class Laptop extends Device implements Connectable, Chargeable {
    public Laptop(int id, String name) {
        super(id, name);
    }

    public Laptop() {
    }

    @Override
    public void turnOn() {
        System.out.println("Laptop is turning on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Laptop is turning off.");
    }

    @Override
    public void connectWifi() {
        System.out.println("Laptop is connecting to Wi-Fi.");
    }

    @Override
    public void charge() {
        System.out.println("Laptop is charging.");
    }
}
