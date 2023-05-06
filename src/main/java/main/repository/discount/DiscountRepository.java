package main.repository.discount;

import main.model.Discount;
import main.repository.discount.model.DiscountRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component("discountRepository")
public class DiscountRepository {
    @Autowired
    DiscountRepoJpa discountRepoJpa;

    public void saveDiscount(Discount discount) {
        discountRepoJpa.save(new DiscountRepoModel(discount.getId().toString(),
                discount.getProductId().toString(),
                discount.getExpiryDate(),
                discount.getDiscountAmount()));
    }

    public Discount searchDiscountBy(String id) {
        return discountRepoJpa.findById(id).map(this::mapDiscount).orElse(null);
    }

    public List<Discount> getDiscountsOf(String productId) throws Exception {
        List<DiscountRepoModel> discountRepoModels = discountRepoJpa.findAllByProductId(productId).orElse(null);

        if (discountRepoModels == null || discountRepoModels.size() == 0) {
            throw new Exception("Nothing found");
        }

        return discountRepoModels.stream().map(this::mapDiscount).toList();
    }

    public void deleteDiscount(String discountId) throws Exception {
        try {
            discountRepoJpa.deleteById(discountId);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Discount not found");
        }
    }

    public Discount mapDiscount(DiscountRepoModel discountRepoModel) {
        Discount discount;
        try {
            discount = new Discount(UUID.fromString(discountRepoModel.id),
                    UUID.fromString(discountRepoModel.productId),
                    discountRepoModel.expiryDate,
                    discountRepoModel.discountAmount);
        } catch (Exception e) {
            return null;
        }
        return discount;
    }

    public List<Discount> getDiscounts() {
        List<DiscountRepoModel> discounts = discountRepoJpa.findAll();
        return discounts.stream().map(this::mapDiscount).toList();
    }
}
