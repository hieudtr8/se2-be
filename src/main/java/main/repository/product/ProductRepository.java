package main.repository.product;

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
        productRepoJpa.save(new ProductRepoModel(
                product.getId().toString(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImages(),
                product.getAmount(),
                product.getColor()
        ));
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
                    productFromDatabase.images,
                    productFromDatabase.amount,
                    productFromDatabase.color
            );
        } catch (Exception e) {
            System.out.println("Invalid product data in database");
            e.printStackTrace();
            return null;
        }
    }

}
