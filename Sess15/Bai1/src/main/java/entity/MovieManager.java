package entity;
import java.util.ArrayList;
import java.util.List;

public class MovieManager<T extends Movie> {
    // Sử dụng Generic List để quản lý danh sách phim
    private List<T> list;

    public MovieManager() {
        this.list = new ArrayList<>();
    }

    // Thêm phim mới
    public void add(T movie) {
        list.add(movie);
        System.out.println("Phim đã được thêm thành công.");
    }

    // Hiển thị danh sách phim hiện có
    public void displayAll() {
        if (list.isEmpty()) {
            System.out.println("Danh sách phim trống!");
            return;
        }
        System.out.println("Danh sách phim:");
        for (T movie : list) {
            System.out.println(movie);
        }
    }

    // Xóa phim theo ID
    public boolean deleteById(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    // Tìm kiếm phim theo ID (Hỗ trợ cho chức năng sửa đổi)
    public T findById(String id) {
        for (T movie : list) {
            if (movie.getId().equalsIgnoreCase(id)) {
                return movie;
            }
        }
        return null;
    }

    // Tìm kiếm phim theo tên
    public void searchByTitle(String title) {
        boolean found = false;
        for (T movie : list) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Phim tìm thấy: " + movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy phim");
        }
    }

    // Lọc phim có rating lớn hơn giá trị tối thiểu nhập vào
    public void filterByRating(double minRating) {
        boolean found = false;
        System.out.println("Phim có rating lớn hơn " + minRating + ":");
        for (T movie : list) {
            if (movie.getRating() > minRating) {
                System.out.println(movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("(Không có phim nào thỏa mãn)");
        }
    }
}