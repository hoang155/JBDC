package entity;

import java.util.ArrayList;

public class AttendanceManager implements Manage<Student> {
    // Sử dụng ArrayList<Student> để quản lý bộ nhớ động
    private final ArrayList<Student> studentList = new ArrayList<>();

    @Override
    public void add(Student item) {
        studentList.add(item);
        System.out.println("Sinh viên đã được thêm thành công.");
    }

    @Override
    public void update(int index, Student item) {
        if (index >= 0 && index < studentList.size()) {
            studentList.set(index, item);
            System.out.println("Sinh viên đã được sửa thành công.");
        } else {
            System.out.println("❌ Lỗi: Vị trí cập nhật không hợp lệ!");
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < studentList.size()) {
            studentList.remove(index);
            System.out.println("Đã xóa thành công sinh viên !");
        } else {
            System.out.println("❌ Lỗi: Vị trí cần xóa không tồn tại!");
        }
    }

    @Override
    public void display() {
        if (studentList.isEmpty()) {
            System.out.println("⚠️ Danh sách sinh viên hiện tại đang trống.");
            return;
        }
        // In ra màn hình kèm số thứ tự hiển thị giống trong ảnh console mẫu (bắt đầu từ 1.)
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println((i + 1) + ". " + studentList.get(i));
        }
    }

    // Helper method: Hỗ trợ tìm vị trí index trong ArrayList dựa theo ID sinh viên
    public int findIndexById(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                return i; // Trả về chỉ số index trong mảng nếu tìm thấy
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy ID
    }

    // Helper method: Kiểm tra danh sách có trống hay không để xử lý menu logic gọn hơn
    public boolean isEmpty() {
        return studentList.isEmpty();
    }
}