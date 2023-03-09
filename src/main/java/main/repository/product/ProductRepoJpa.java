package main.repository.product;

import main.repository.product.model.ProductRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepoJpa extends JpaRepository<ProductRepoModel, String> {
    Optional<ProductRepoModel> findByName(String name);
}
