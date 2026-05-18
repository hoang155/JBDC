package org.example;

public abstract class Device {
    private int id;
    private String name;

    public abstract void turnOn();
    public abstract void turnOff();

    public Device(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Device() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
