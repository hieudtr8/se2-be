package main.repository.category.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProductCategoryCompositeKey implements Serializable {
    private String productId;
    private String categoryId;
}
