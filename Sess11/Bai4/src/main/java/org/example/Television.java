package org.example;

public class Television extends Device implements Connectable {
    public Television(int id, String name) {
        super(id, name);
    }

    public Television() {
    }

    @Override
    public void turnOn() {
        System.out.println("Television is turning on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Television is turning off.");
    }

    @Override
    public void connectWifi() {
        System.out.println("Television is connecting to Wi-Fi.");
    }
}
