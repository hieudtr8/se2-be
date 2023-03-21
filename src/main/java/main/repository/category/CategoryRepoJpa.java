package main.repository.category;

import main.repository.category.model.CategoryRepoModel;
import main.repository.product.model.ProductRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepoJpa extends JpaRepository<CategoryRepoModel, String> {
    Optional<CategoryRepoModel> findByName(String name);
}
