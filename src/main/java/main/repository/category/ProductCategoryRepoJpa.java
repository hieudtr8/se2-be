package main.repository.category;

import main.repository.category.model.ProductCategoryRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepoJpa extends JpaRepository<ProductCategoryRepoModel, String> {
    List<ProductCategoryRepoModel> findAllByProductId(String productId);

    List<ProductCategoryRepoModel> findAllByCategoryId(String categoryId);

    Optional<ProductCategoryRepoModel> findByProductIdAndCategoryId(String productId, String categoryId);

    void deleteByProductIdAndCategoryId(String productId, String categoryId);
}
