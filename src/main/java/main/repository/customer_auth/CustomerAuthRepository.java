package main.repository.customer_auth;

import main.model.CustomerAuth;
import main.model.CustomerProfile;
import main.model.Product;
import main.repository.customer_auth.model.CustomerAuthRepoModel;
import main.repository.customer_profile.model.CustomerProfileRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("customerAuthRepository")
public class CustomerAuthRepository {
    @Autowired
    private CustomerAuthRepoJpa customerAuthRepoJpa;

    public void saveCustomerAuth(CustomerAuth customerAuth) {
        try {
            CustomerProfileRepoModel profileToRepo = new CustomerProfileRepoModel(
                    customerAuth.getCustomerProfile().getId().toString(),
                    customerAuth.getCustomerProfile().getName(),
                    customerAuth.getCustomerProfile().getAge(),
                    customerAuth.getCustomerProfile().getPhone(),
                    customerAuth.getCustomerProfile().getAddress(),
                    customerAuth.getCustomerProfile().getAvatar()
            );
            customerAuthRepoJpa.save(new CustomerAuthRepoModel(
                    customerAuth.getId().toString(),
                    customerAuth.getEmail(),
                    customerAuth.getPassword(),
                    profileToRepo
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public CustomerAuth getCustomerAuthById(UUID id) {
        Optional<CustomerAuthRepoModel> customerAuth = customerAuthRepoJpa.findById(id.toString());
        return customerAuth.map(this::mapCustomerAuth).orElse(null);
    }
    public List<CustomerAuth> getAllCustomerAuth() {
        List<CustomerAuthRepoModel> customerAuths = customerAuthRepoJpa.findAll();
        return customerAuths.stream().map(this::mapCustomerAuth).toList();
    }
    public Boolean isExistCustomerAuthEmail(String email) {
        return customerAuthRepoJpa.existsByEmail(email);
    }

    private CustomerAuth mapCustomerAuth(CustomerAuthRepoModel customerAuthFromDatabase) {
        try {
            CustomerProfile profileToModel = new CustomerProfile(
                    UUID.fromString(customerAuthFromDatabase.customerProfile.id),
                    customerAuthFromDatabase.customerProfile.name,
                    customerAuthFromDatabase.customerProfile.age,
                    customerAuthFromDatabase.customerProfile.phone,
                    customerAuthFromDatabase.customerProfile.address,
                    customerAuthFromDatabase.customerProfile.avatar
            );
            return new CustomerAuth(
                    UUID.fromString(customerAuthFromDatabase.id),
                    customerAuthFromDatabase.email,
                    customerAuthFromDatabase.password,
                    profileToModel
            );
        } catch (Exception e) {
            System.out.println("Invalid customer auth data in database");
            e.printStackTrace();
            return null;
        }
    }
}
