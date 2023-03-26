package main.repository.customer_profile.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import main.repository.customer_auth.model.CustomerAuthRepoModel;

import java.util.List;

@Entity
@Table(name = "customer_profile")
public class CustomerProfileRepoModel {
    @Column(nullable = false, length = 100)
    @Id
    public String id;
    @Column(nullable = false, length = 100)
    public String name;
    public int age;
    public String phone;
    public String address;
    public String avatar;
    @OneToOne(mappedBy = "customerProfile")
    @JsonBackReference
    public CustomerAuthRepoModel customerAuth;

    public CustomerProfileRepoModel(String id, String name, int age, String phone, String address, String avatar) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
    }

    public CustomerProfileRepoModel() {

    }
}
