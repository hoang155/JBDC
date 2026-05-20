package org.example;

import entity.Invoice;

import java.util.ArrayList;

public class InvoiceManager implements Manage<Invoice> {
    private final ArrayList<Invoice> invoiceList = new ArrayList<>();

    @Override
    public void add(Invoice item) {
        invoiceList.add(item);
        System.out.println("Hóa đơn đã được thêm thành công.");
    }

    @Override
    public void update(int index, Invoice item) {
        if (index >= 0 && index < invoiceList.size()) {
            invoiceList.set(index, item);
            System.out.println("Hóa đơn đã được sửa thành công.");
        } else {
            System.out.println("❌ Lỗi: Vị trí cập nhật không hợp lệ!");
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < invoiceList.size()) {
            invoiceList.remove(index);
            System.out.println("Hóa đơn đã được xóa thành công.");
        } else {
            System.out.println("❌ Lỗi: Vị trí cần xóa không tồn tại!");
        }
    }

    @Override
    public void display() {
        if (invoiceList.isEmpty()) {
            System.out.println("⚠️ Danh sách hóa đơn hiện tại đang trống.");
            return;
        }
        for (int i = 0; i < invoiceList.size(); i++) {
            System.out.println((i + 1) + ". " + invoiceList.get(i));
        }
    }

    // Helper method: Tìm chỉ số index trong ArrayList theo ID hệ thống
    public int findIndexById(int id) {
        for (int i = 0; i < invoiceList.size(); i++) {
            if (invoiceList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    // Helper method: Kiểm tra danh sách trống
    public boolean isEmpty() {
        return invoiceList.isEmpty();
    }
}