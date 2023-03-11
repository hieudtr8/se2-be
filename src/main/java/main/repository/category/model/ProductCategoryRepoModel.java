package main.repository.category.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_category")
@IdClass(ProductCategoryCompositeKey.class)
public class ProductCategoryRepoModel {

    @Column(nullable = false, length = 100)
    @Id
    public String productId;

    @Column(nullable = false, length = 100)
    @Id
    public String categoryId;

    public ProductCategoryRepoModel(String productId, String categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public ProductCategoryRepoModel() {

    }
}
