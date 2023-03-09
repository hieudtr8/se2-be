package main.repository.category.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "category")
public class CategoryRepoModel {

    @Column(nullable = false)
    @Id
    public String id;
    public String name;

    public CategoryRepoModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryRepoModel() {

    }
}
