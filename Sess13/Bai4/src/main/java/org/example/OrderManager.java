package org.example;

import entity.Order;

import java.util.ArrayList;

public class OrderManager implements Manage<Order> {
    // Sử dụng ArrayList<Order> để quản lý danh sách đơn hàng động
    private final ArrayList<Order> orderList = new ArrayList<>();

    @Override
    public void add(Order item) {
        orderList.add(item);
        System.out.println("Đơn hàng đã được thêm thành công.");
    }

    @Override
    public void update(int index, Order item) {
        if (index >= 0 && index < orderList.size()) {
            orderList.set(index, item);
            System.out.println("Đơn hàng đã được sửa thành công.");
        } else {
            System.out.println("❌ Lỗi: Vị trí cập nhật không hợp lệ.");
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < orderList.size()) {
            orderList.remove(index);
            System.out.println("Đơn hàng đã được xóa thành công.");
        } else {
            System.out.println("❌ Lỗi: Vị trí cần xóa không tồn tại.");
        }
    }

    @Override
    public void display() {
        if (orderList.isEmpty()) {
            System.out.println("⚠️ Danh sách đơn hàng hiện tại đang trống.");
            return;
        }
        // Hiển thị STT bắt đầu từ 1 kèm thông tin tương tự mẫu console trong ảnh
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println((i + 1) + ". " + orderList.get(i));
        }
    }

    // Helper method: Tìm chỉ số index trong ArrayList bằng Mã đơn hàng (không phân biệt chữ hoa thường)
    public int findIndexByCode(String orderCode) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderCode().equalsIgnoreCase(orderCode)) {
                return i;
            }
        }
        return -1;
    }

    // Helper method: Kiểm tra danh sách đơn hàng trống
    public boolean isEmpty() {
        return orderList.isEmpty();
    }
}