package main.repository.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.model.Product;
import main.repository.product.model.ProductRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("productRepository")
public class ProductRepository {

    @Autowired
    private ProductRepoJpa productRepoJpa;

    public void saveProduct(Product product) {
        try {
            productRepoJpa.save(new ProductRepoModel(
                    product.getId().toString(),
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    new ObjectMapper().writeValueAsString(product.getImages()),
                    product.getAmount(),
                    product.getBrand()
            ));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProductById(UUID id) {
        Optional<ProductRepoModel> product = productRepoJpa.findById(id.toString());
        return product.map(this::mapProduct).orElse(null);
    }

    public List<Product> getAllProducts() {
        List<ProductRepoModel> products = productRepoJpa.findAll();
        return products.stream().map(this::mapProduct).toList();
    }

    public Product getProductByName(String name) {
        return productRepoJpa.findByName(name).map(this::mapProduct).orElse(null);
    }

    public void deleteProductById(UUID id) throws Exception {
        try {
            productRepoJpa.deleteById(id.toString());
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Product not found");
        }
    }


    private Product mapProduct(ProductRepoModel productFromDatabase) {
        try {
            return new Product(
                    UUID.fromString(productFromDatabase.id),
                    productFromDatabase.name,
                    productFromDatabase.price,
                    productFromDatabase.description,
                    new ObjectMapper().readValue(productFromDatabase.images, new TypeReference<List<String>>() {}),
                    productFromDatabase.amount,
                    productFromDatabase.brand
            );
        } catch (Exception e) {
            System.out.println("Invalid product data in database");
            e.printStackTrace();
            return null;
        }
    }

}
