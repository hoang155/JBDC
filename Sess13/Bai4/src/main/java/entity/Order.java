package entity;

public class Order {
    private String orderCode;
    private String customerName;

    public Order() {
    }

    public Order(String orderCode, String customerName) {
        this.orderCode = orderCode;
        this.customerName = customerName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Mã đơn hàng: " + orderCode + ", Tên khách hàng: " + customerName;
    }
}