package main.service.customer_auth;

import main.model.CustomerAuth;
import main.repository.customer_auth.CustomerAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EditCustomerAuthService {
    @Autowired
    private CustomerAuthRepository customerAuthRepository;
    public static class CustomerAuthData {
        String password;
        public CustomerAuthData(String password) {
            this.password = password;
        }
    }
    public CustomerAuth editCustomerProfile(UUID id, CustomerAuthData customerAuthData) throws Exception {
        CustomerAuth customerAuth = customerAuthRepository.getCustomerAuthById(id);
        if (customerAuth == null) {
            throw new Exception("Customer auth not found!");
        }
        if (customerAuthData.password != null ) {
            customerAuth.setPassword(customerAuthData.password);
        }
        customerAuthRepository.saveCustomerAuth(customerAuth);
        return customerAuth;
    }
}
