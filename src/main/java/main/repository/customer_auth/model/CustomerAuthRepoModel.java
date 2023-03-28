package main.repository.customer_auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import main.model.CustomerProfile;
import main.repository.customer_profile.model.CustomerProfileRepoModel;

import java.util.UUID;

@Entity
@Table(name = "customer_auth",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class CustomerAuthRepoModel {
    @Column(nullable = false, length = 100)
    @Id
    public String id;
    @Column(nullable = false, length = 100)
    public String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    public CustomerProfileRepoModel customerProfile;


    public CustomerAuthRepoModel(String email, String password, CustomerProfileRepoModel customerProfile) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.customerProfile = customerProfile;
    }

    public CustomerAuthRepoModel(String id, String email, String password, CustomerProfileRepoModel customerProfile) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.customerProfile = customerProfile;
    }

    public CustomerAuthRepoModel() {
        
    }


    public CustomerProfileRepoModel getCustomerProfile() {
        return customerProfile;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
