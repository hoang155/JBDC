package entity;

import java.util.List;
import java.util.function.Predicate;

public interface ProductProcessor {

    double calculateTotalValue(List<Product> products);

    static void printProductList(List<Product> products) {
        System.out.println("--- DANH SÁCH SẢN PHẨM ---");
        products.stream().forEach(System.out::println);
    }

    default boolean hasExpensiveProduct(List<Product> products) {
        Predicate<Product> isExpensive = p -> p.getPrice() > 100;

        for (Product p : products) {
            if (isExpensive.test(p)) { // Sử dụng hàm .test() của Predicate
                return true;
            }
        }
        return false;
    }
}