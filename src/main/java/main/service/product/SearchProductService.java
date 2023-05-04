package main.service.product;

import main.model.Product;
import main.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(UUID id) throws Exception {
        Product product = productRepository.getProductById(id);
        if (product == null)
            throw new Exception("Product with id " + id + " does not exist");
        return product;
    }

    public List<Product> getProductsByIds(List<UUID> ids) throws Exception {
        List<Product> products = productRepository.getAllProducts();
        // products is immutable, so we need to create a new list
        products = products.stream().filter(product -> ids.contains(product.getId())).toList();
        return products;
    }

    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }
}
