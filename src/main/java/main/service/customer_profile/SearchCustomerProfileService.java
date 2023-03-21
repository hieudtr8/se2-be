package main.service.customer_profile;

import main.model.CustomerProfile;
import main.repository.customer_profile.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchCustomerProfileService {
    @Autowired
    private CustomerProfileRepository customerProfileRepository;
    public CustomerProfile getCustomerProfileById(UUID id) throws Exception {
        CustomerProfile customerProfile = customerProfileRepository.getCustomerProfileById(id);
        if (customerProfile == null)
            throw new Exception("Customer with id " + id + " does not exist");
        return  customerProfile;
    }
    public List<CustomerProfile> getCustomerProfiles () {
        return customerProfileRepository.getAllCustomerProfile();
    }
}
