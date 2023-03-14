package main.repository.product.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductRepoModel {
    @Column(nullable = false, length = 100)
    @Id
    public String id;
    public String name;
    public double price;
    public String description;
    public String images;
    public int amount;
    public String brand;

    public ProductRepoModel(
            String id,
            String name,
            double price,
            String description,
            String images,
            int amount,
            String brand
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.images = images;
        this.amount = amount;
        this.brand = brand;
    }


    public ProductRepoModel() {

    }
}
