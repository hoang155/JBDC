package entity;

import java.time.LocalDate;
import java.util.Optional;

public class Order {
    private int id;
    private String customerName;
    private LocalDate createdDate;
    private Optional<LocalDate> deliveryDate;

    public Order() {
        this.deliveryDate = Optional.empty();
    }

    public Order(int id, String customerName, LocalDate createdDate, LocalDate deliveryDate) {
        this.id = id;
        this.customerName = customerName;
        this.createdDate = createdDate;
        this.deliveryDate = Optional.ofNullable(deliveryDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Optional<LocalDate> getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = Optional.ofNullable(deliveryDate);
    }

    // Phương thức hiển thị thông tin theo định dạng yêu cầu của đề bài
    public String toDisplayString() {
        // Áp dụng Optional.map() để lấy chuỗi ngày, nếu trống (null) thì thay bằng "Chưa giao"
        String deliveryStr = deliveryDate
                .map(LocalDate::toString)
                .orElse("Chưa giao");

        return String.format("ID: %-3d | Tên KH: %-15s | Ngày đặt: %-10s | Ngày giao: %-10s",
                id, customerName, createdDate, deliveryStr);
    }
}
