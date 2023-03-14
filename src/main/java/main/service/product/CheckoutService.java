package main.service.product;

import main.model.Product;
import main.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckoutService {
    @Autowired
    private ProductRepository productRepository;

    public Product checkoutProduct(UUID id, int quantity) throws Exception {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new Exception("Product not found");
        }
        if (product.getAmount() < quantity) {
            throw new Exception("Not enough products");
        }
        product.setAmount(product.getAmount() - quantity);
        productRepository.saveProduct(product);
        return product;
    }
}
