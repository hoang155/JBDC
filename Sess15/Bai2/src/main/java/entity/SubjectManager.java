package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectManager<T extends Subject> {
    private List<T> subjects;

    public SubjectManager() {
        this.subjects = new ArrayList<>();
    }

    // Thêm môn học mới
    public void addSubject(T subject) {
        subjects.add(subject);
        System.out.println("Đã thêm môn học thành công.");
    }

    // Hiển thị toàn bộ danh sách môn học hiện có bằng Stream / For-each
    public void displayAll() {
        if (subjects.isEmpty()) {
            System.out.println("Danh sách môn học hiện đang trống.");
            return;
        }
        System.out.println("--- DANH SÁCH MÔN HỌC ---");
        subjects.forEach(System.out.println);
    }

    // Xóa môn học theo mã code (Sử dụng Stream/Optional hoặc removeIf)
    public boolean deleteByCode(String code) {
        Optional<T> target = subjects.stream()
                .filter(s -> s.getCode().equalsIgnoreCase(code))
                .findFirst();

        if (target.isPresent()) {
            subjects.remove(target.get());
            return true;
        }
        return false;
    }

    // Tìm kiếm môn học theo tên (Sử dụng Stream + Optional theo yêu cầu ảnh 1)
    public Optional<T> searchByName(String name) {
        return subjects.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst();
    }

    // Lọc danh sách môn học có số tín chỉ credits > 3 bằng Stream API
    public void filterByCredits() {
        System.out.println("--- CÁC MÔN HỌC CÓ SỐ TÍN CHỈ > 3 ---");
        long count = subjects.stream()
                .filter(s -> s.getCredits() > 3)
                .peek(System.out.println)
                .count();

        if (count == 0) {
            System.out.println("(Không có môn học nào có số tín chỉ lớn hơn 3)");
        }
    }
}