package org.example;

public abstract class Shape {
    protected String name;

    public Shape(String name) {
    }

    abstract public double getArea();
    abstract public double getPerimeter();
    abstract public void displayInfo();
}
