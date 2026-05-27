package entity;

import java.util.List;

public class ProductProcessorImpl implements ProductProcessor {
    @Override
    public double calculateTotalValue(List<Product> products) {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}
