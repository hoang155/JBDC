package org.example;

public class SmartPhone extends Device implements Connectable, Chargeable {
    public SmartPhone(int id, String name) {
        super(id, name);
    }

    public SmartPhone() {
    }

    @Override
    public void turnOn() {
        System.out.println("SmartPhone is turning on.");
    }

    @Override
    public void turnOff() {
        System.out.println("SmartPhone is turning off.");
    }

    @Override
    public void connectWifi() {
        System.out.println("SmartPhone is connecting to Wi-Fi.");
    }

    @Override
    public void charge() {
        System.out.println("SmartPhone is charging.");
    }
}
