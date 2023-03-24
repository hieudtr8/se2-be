package main.model;

import java.util.Objects;
import java.util.UUID;

public class ProductCategory {
    private UUID productId;
    private UUID categoryId;

    public ProductCategory(UUID productId, UUID categoryId) {
        setProductId(productId);
        setCategoryId(categoryId);
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        validateId(productId);
        this.productId = productId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        validateId(categoryId);
        this.categoryId = categoryId;
    }

    private void validateId(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
    }

}
