package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManagement manager = new MovieManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MOVIE MANAGEMENT MENU ---");
            System.out.println("1. Add Movie");
            System.out.println("2. List Movies");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter director: ");
                        String director = sc.nextLine();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(sc.nextLine());

                        if (title.isEmpty()) throw new Exception("Title cannot be empty!");
                        manager.addMovie(title, director, year);
                        break;

                    case 2:
                        manager.listMovies();
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        int uId = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter new title: ");
                        String uTitle = sc.nextLine();
                        System.out.print("Enter new director: ");
                        String uDir = sc.nextLine();
                        System.out.print("Enter new year: ");
                        int uYear = Integer.parseInt(sc.nextLine());
                        manager.updateMovie(uId, uTitle, uDir, uYear);
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        int dId = Integer.parseInt(sc.nextLine());
                        manager.deleteMovie(dId);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for Year or ID.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}