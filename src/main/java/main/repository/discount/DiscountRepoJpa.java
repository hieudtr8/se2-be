package main.repository.discount;

import main.repository.discount.model.DiscountRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepoJpa extends JpaRepository<DiscountRepoModel, String> {
    Optional<List<DiscountRepoModel>> findAllByProductId(String productId);
}
