package main.repository.customer_profile;

import main.model.CustomerProfile;
import main.model.Product;
import main.repository.customer_profile.model.CustomerProfileRepoModel;
import main.repository.product.model.ProductRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("customerProfileRepository")
public class CustomerProfileRepository {
    @Autowired
    private CustomerProfileRepoJpa customerProfileRepoJpa;
    public void saveCustomerProfile(CustomerProfile customerProfile) {
        customerProfileRepoJpa.save(new CustomerProfileRepoModel(
                customerProfile.getId().toString(),
                customerProfile.getName(),
                customerProfile.getAge(),
                customerProfile.getPhone(),
                customerProfile.getAddress(),
                customerProfile.getAvatar()
        ));
    }
    public CustomerProfile getCustomerProfileById(UUID id) {
        Optional<CustomerProfileRepoModel> customerProfile = customerProfileRepoJpa.findById(id.toString());
        return customerProfile.map(this::mapCustomerProfile).orElse(null);
    }

    public List<CustomerProfile> getAllCustomerProfile() {
        List<CustomerProfileRepoModel> customerProfiles = customerProfileRepoJpa.findAll();
        return customerProfiles.stream().map(this::mapCustomerProfile).toList();
    }

    private CustomerProfile mapCustomerProfile(CustomerProfileRepoModel customerProfileFromDatabase) {
        try {
            return new CustomerProfile(
                    UUID.fromString(customerProfileFromDatabase.id),
                    customerProfileFromDatabase.name,
                    customerProfileFromDatabase.age,
                    customerProfileFromDatabase.phone,
                    customerProfileFromDatabase.address,
                    customerProfileFromDatabase.avatar
            );
        } catch (Exception e) {
            System.out.println("Invalid customer profile data in database");
            e.printStackTrace();
            return null;
        }
    }
}
