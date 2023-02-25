package main.repository.product.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductRepoModel {
    @Column(nullable = false)
    @Id
    public String id;
    public String name;
    public double price;
    public String description;
    @ElementCollection
    public List<String> images;
    public int amount;
    public String color;

    public ProductRepoModel(
            String id,
            String name,
            double price,
            String description,
            List<String> images,
            int amount,
            String color
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.images = images;
        this.amount = amount;
        this.color = color;
    }

    public ProductRepoModel() {

    }
}
