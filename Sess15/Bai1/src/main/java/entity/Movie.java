package entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Movie {
    private String id;
    private String title;
    private String director;
    private LocalDate releasedDate;
    private double rating;

    public Movie(String id, String title, String director, LocalDate releasedDate, double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releasedDate = releasedDate;
        this.rating = rating;
    }

    // Getter và Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public LocalDate getReleasedDate() { return releasedDate; }
    public void setReleasedDate(LocalDate releasedDate) { this.releasedDate = releasedDate; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        // Định dạng ngày hiển thị ra màn hình theo kiểu dd-MM-yyyy giống mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releasedDate=" + releasedDate.format(formatter) +
                ", rating=" + rating +
                '}';
    }
}