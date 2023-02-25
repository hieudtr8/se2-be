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
            String color) throws Exception {
        Product product = new Product(name, price, description, images, amount, color);
        if (productRepository.getProductsByName(name).size() > 0)
            throw new Exception("Product " + product.getName() + " already exists");
        productRepository.saveProduct(product);
        return product;
    }
}
