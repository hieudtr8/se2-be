package main.repository.customer_auth.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name= "customer_auth",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class CustomerAuthRepoModel {
    @Id
    @Column(name ="id", nullable = false, length = 100)
    private String id;
    @Column(nullable = false, length = 100)
    private String username;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;

    public Set<RoleRepoModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleRepoModel> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleRepoModel> roles = new HashSet<>();
    public CustomerAuthRepoModel(UUID id, String username, String email, String password) {
        this.id = id.toString();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public CustomerAuthRepoModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
