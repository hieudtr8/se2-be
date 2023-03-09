package main.repository.category.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_category")
@IdClass(ProductCategoryCompositeKey.class)
public class ProductCategoryRepoModel {
    @Id
    public String productId;
    @Id
    public String categoryId;

    public ProductCategoryRepoModel(String productId, String categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public ProductCategoryRepoModel() {

    }
}
