package main.service.customer_profile;

import main.model.CustomerProfile;
import main.repository.customer_profile.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerProfileService {
    @Autowired
    private CustomerProfileRepository customerProfileRepository;

    public CustomerProfile createCustomerProfile(String name, Integer age, String phone, String address, String avatar) throws Exception {
        CustomerProfile customerProfile = new CustomerProfile(name,age,phone,address,avatar);
        customerProfileRepository.saveCustomerProfile(customerProfile);
        return customerProfile;

    }
}
