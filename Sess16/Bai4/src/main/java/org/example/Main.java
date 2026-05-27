package org.example;

import entity.Product;
import entity.ProductProcessor;
import entity.ProductProcessorImpl;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Chuột Gaming", 45.0));
        products.add(new Product("Bàn phím cơ", 120.5)); // Sản phẩm > 100
        products.add(new Product("Lót chuột", 15.0));

        // Khởi tạo đối tượng xử lý
        ProductProcessor processor = new ProductProcessorImpl();

        // 2. In toàn bộ danh sách bằng phương thức static của Interface
        ProductProcessor.printProductList(products);
        System.out.println();

        // 3. Kiểm tra sản phẩm đắt tiền (> 100) bằng phương thức default
        if (processor.hasExpensiveProduct(products)) {
            System.out.println("Kết quả kiểm tra: Hệ thống phát hiện có sản phẩm đắt tiền (> 100) trong danh sách.");
        } else {
            System.out.println("Không có sản phẩm đắt tiền");
        }

        // 4. Tính và hiển thị tổng giá trị danh sách sản phẩm
        double totalValue = processor.calculateTotalValue(products);
        System.out.printf("Tổng giá trị tất cả sản phẩm: %.2f\n", totalValue);
    }
}
