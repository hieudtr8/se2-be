package main.service.product;

import main.model.Product;
import main.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreateProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(
            String name,
            Double price,
            String description,
            List<String> images,
            Integer amount,
            String brand) throws Exception {
        Product product = new Product(name, price, description, images, amount, brand);
        if (productRepository.getProductByName(name) != null)
            throw new Exception("Product " + product.getName() + " already exists");
        productRepository.saveProduct(product);
        return product;
    }
}
