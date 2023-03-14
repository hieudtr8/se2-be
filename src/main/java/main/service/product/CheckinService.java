package main.service.product;

import main.model.Product;
import main.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CheckinService {
    @Autowired
    private ProductRepository productRepository;

    public Product checkinProduct(UUID id, int quantity) throws Exception {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new Exception("Product not found");
        }
        product.setAmount(product.getAmount() + quantity);
        System.out.println(product);
        productRepository.saveProduct(product);
        return product;
    }
}
