package entity;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addToCart(Product product, int quantity) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ chưa
        for (CartItem item : items) {
            if (item.getProduct().getId().equalsIgnoreCase(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity); // Tăng số lượng
                System.out.println("Đã cập nhật số lượng sản phẩm trong giỏ hàng.");
                return;
            }
        }
        // Nếu chưa tồn tại thì thêm mới một CartItem
        items.add(new CartItem(product, quantity));
        System.out.println("Đã thêm sản phẩm mới vào giỏ hàng.");
    }

    // Xóa sản phẩm khỏi giỏ hàng theo mã
    public void removeFromCart(String productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId().equalsIgnoreCase(productId)) {
                items.remove(i);
                System.out.println("Đã xóa sản phẩm khỏi giỏ hàng thành công.");
                return;
            }
        }
        // Nếu duyệt hết danh sách mà không thấy -> Phát sinh ngoại lệ tương ứng yêu cầu ở ảnh 3
        throw new IllegalArgumentException("Lỗi: Không tìm thấy sản phẩm trong giỏ hàng!");
    }

    // In toàn bộ danh sách sản phẩm trong giỏ hàng
    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
            return;
        }
        System.out.println("--- CÁC SẢN PHẨM TRONG GIỎ ---");
        for (CartItem item : items) {
            System.out.printf(" - %s | Số lượng: %d | Thành tiền: %,.0f đ\n",
                    item.getProduct().getName(), item.getQuantity(), item.getThanhTien());
        }
    }

    // Tính và in tổng tiền cần thanh toán
    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Giỏ hàng trống. Không có gì để thanh toán!");
            return;
        }
        double tongTien = 0;
        for (CartItem item : items) {
            tongTien += item.getThanhTien();
        }
        System.out.println("\n============ HÓA ĐƠN ============");
        displayCart();
        System.out.println("---------------------------------");
        System.out.printf("TỔNG TIỀN CẦN THANH TOÁN: %,.0f đ\n", tongTien);
        System.out.println("=================================");
        items.clear(); // Xóa giỏ hàng sau khi thanh toán xong
    }
}