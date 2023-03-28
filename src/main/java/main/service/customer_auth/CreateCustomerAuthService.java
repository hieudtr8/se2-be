package main.service.customer_auth;

import main.model.CustomerAuth;
import main.model.CustomerProfile;
import main.repository.customer_auth.CustomerAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CreateCustomerAuthService {
    @Autowired
    private CustomerAuthRepository customerAuthRepository;

    public CustomerAuth createCustomerAuth(
            String email,
            String password
    ) throws Exception {
        CustomerProfile profileToModel = new CustomerProfile("", 0,"","","");
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        CustomerAuth customerAuth = new CustomerAuth(email, encodedPassword, profileToModel);
        if (customerAuthRepository.isExistCustomerAuthEmail(email)) {
            throw new Exception("Email: " + customerAuth.getEmail() + " already exists");
        }
        customerAuthRepository.saveCustomerAuth(customerAuth);
        return customerAuth;
    }
}
