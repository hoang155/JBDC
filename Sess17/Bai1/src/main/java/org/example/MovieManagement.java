package org.example;
import java.sql.*;

public class MovieManagement {
    private String url = "jdbc:postgresql://localhost:5432/your_database";
    private String user = "your_username";
    private String password = "your_password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addMovie(String title, String director, int year) {
        String sql = "{call add_movie(?, ?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, director);
            stmt.setInt(3, year);
            stmt.execute();
            System.out.println("Movie added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding movie: " + e.getMessage());
        }
    }

    public void listMovies() {
        String sql = "SELECT * FROM list_movies()"; // Calling the function
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Movie List ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Title: %s | Director: %s | Year: %d\n",
                        rs.getInt("id"), rs.getString("title"),
                        rs.getString("director"), rs.getInt("release_year"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing movies: " + e.getMessage());
        }
    }

    public void updateMovie(int id, String title, String director, int year) {
        String sql = "{call update_movie(?, ?, ?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, director);
            stmt.setInt(4, year);
            stmt.execute();
            System.out.println("Movie updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating movie: " + e.getMessage());
        }
    }

    public void deleteMovie(int id) {
        String sql = "{call delete_movie(?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Movie deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting movie: " + e.getMessage());
        }
    }
}