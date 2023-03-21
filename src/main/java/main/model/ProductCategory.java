package main.model;

import java.util.Objects;

public class ProductCategory {
    private String productId;
    private String categoryId;

    public ProductCategory(String productId, String categoryId) {
        setProductId(productId);
        setCategoryId(categoryId);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        validateId(productId);
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        validateId(categoryId);
        this.categoryId = categoryId;
    }

    private void validateId(String productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Id can not be null");
        }
    }

}
