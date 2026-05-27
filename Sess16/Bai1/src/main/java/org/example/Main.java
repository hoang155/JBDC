package org.example;

import entity.Product;

import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Map<Integer, Product> productMap = new java.util.HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    static void main() {
        System.out.println("--- Product Management System ---");
        System.out.println("1. Add Product");
        System.out.println("2. Edit Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Display Product");
        System.out.println("5. Filter Product (Price>100)");
        System.out.println("6. Total Value of Products");
        System.out.println("0. Exit");
        while (true) {
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> editProduct();
                case 3 -> deleteProduct();
                case 4 -> displayProduct();
                case 5 -> filterProduct();
                case 6 -> totalValue();
                case 0 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        productMap.put(id, new Product(id, name, price));
        System.out.println("Product added successfully.");
    }
    private static void editProduct() {
        System.out.print("Enter Product ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (productMap.containsKey(id)) {
            Product product = productMap.get(id);

            System.out.print("Enter new Product Name: ");
            String newName = scanner.nextLine();
            product.setName(newName);

            System.out.print("Enter new Product Price: ");
            double newPrice = scanner.nextDouble();
            product.setPrice(newPrice);

            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }
    private static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();

        if (productMap.containsKey(id)) {
            productMap.remove(id);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }
    private static void displayProduct() {
        if (productMap.isEmpty()) {
            System.out.println("No products to display.");
        } else {
            System.out.println("Product List:");
            productMap.values().stream()
                    .forEach(System.out::println);
        }
    }
    private static void filterProduct() {
        System.out.println("Products with price greater than 100:");
        productMap.values().stream()
                .filter(p -> p.getPrice() > 100)
                .forEach(System.out::println);
    }
    private static void totalValue() {
        double total = productMap.values().stream()
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total value of products: " + total);
    }
}
