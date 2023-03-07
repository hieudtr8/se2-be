package main.repository.product;

import main.repository.product.model.ProductRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepoJpa extends JpaRepository<ProductRepoModel, String> {
    List<ProductRepoModel> findByName(String name);
}
