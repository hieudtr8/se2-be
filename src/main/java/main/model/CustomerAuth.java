package main.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import main.repository.customer_profile.model.CustomerProfileRepoModel;

import java.util.UUID;

public class CustomerAuth {
    private UUID id;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private CustomerProfile customerProfile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        validateEmail(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        validatePassword(password);
        this.password = password;
    }


    public UUID getId() {
        return id;
    }

    public CustomerProfile getCustomerProfile() {
        return customerProfile;
    }

    public CustomerAuth(String email, String password, CustomerProfile customerProfile) throws Exception {
        this.id = UUID.randomUUID();
        setEmail(email);
        setPassword(password);
        this.customerProfile = customerProfile;
    }

    public CustomerAuth(UUID id, String email, String password, CustomerProfile customerProfile) throws Exception {
        this.id = id;
        setEmail(email);
        setPassword(password);
        this.customerProfile = customerProfile;
    }


    public static void validateEmail(String email) throws Exception {
        if (email == null)
            throw new Exception("Email can't be null");
        if (email.length() == 0)
            throw new Exception("Email can't be empty");
        if (!email.matches("^(.+)@(\\S+)$"))
            throw new Exception("Not valid email");
    }

    public static void validatePassword(String password) throws Exception {
        if (password == null)
            throw new Exception("Password can't be null");
        if (password.length() < 8) {
            throw new Exception("Password length must be larger than 8 characters");
        }
    }
}