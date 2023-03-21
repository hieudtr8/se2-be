package main.repository.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "adminAuth")
public class AdminAuthRepoModel {
    @Column(nullable = false, length = 100)
    @Id
    public String id;

    @Column(nullable = false, length = 100)
    public String password;

    public AdminAuthRepoModel(String id,String password) {
        this.id = id;
        this.password = password;
    }

    public AdminAuthRepoModel() {

    }


}
