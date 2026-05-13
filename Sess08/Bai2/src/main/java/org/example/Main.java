package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Book b = new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99);
        System.out.println("Title: " + b.getTitle() + ", Author: " + b.getAuthor() + "Price: " + b.getPrice());
    }
}
