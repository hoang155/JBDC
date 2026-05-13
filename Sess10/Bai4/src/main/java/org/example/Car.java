package org.example;

public class Car {
    private int currentSpeed = 0;

    // 1. Tăng tốc mặc định: +10 km/h
    public void accelerate() {
        currentSpeed += 10;
        System.out.println("Car accelerates by default: +10 km/h");
    }

    // 2. Tăng tốc theo giá trị truyền vào
    public void accelerate(int speed) {
        currentSpeed += speed;
        System.out.println("Car accelerates by " + speed + " km/h");
    }

    // 3. Tăng tốc dựa trên công thức: tốc độ = speed * seconds
    public void accelerate(int speed, int seconds) {
        int increase = speed * seconds;
        currentSpeed += increase;
        System.out.println("Car accelerates " + increase + " km/h (speed x time)");
    }

    // Hiển thị trạng thái tốc độ hiện tại
    public void printStatus() {
        System.out.println("Current speed: " + currentSpeed + " km/h\n");
    }
}
