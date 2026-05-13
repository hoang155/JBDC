package org.example;

public class Mammal extends Animal {
    private boolean hasFur;

    public Mammal(String name, int age, boolean hasFur) {
        super(name, age); // Gọi constructor cha
        this.hasFur = hasFur;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println(", Has Fur: " + hasFur);
    }
}
