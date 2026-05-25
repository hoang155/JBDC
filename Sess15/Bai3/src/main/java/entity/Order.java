package entity;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products;

    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
    }

    public int getOrderId() { return orderId; }
    public List<Product> getProducts() { return products; }

    // Thêm sản phẩm vào đơn hàng
    public void addProduct(Product product) {
        this.products.add(product);
    }

    // Tính tổng tiền đơn hàng
    public double tinhTongTien() {
        double tong = 0;
        for (Product p : products) {
            tong += p.getPrice();
        }
        return tong;
    }
}