package main.service.customer_auth;

import main.model.CustomerAuth;
import main.repository.customer_auth.CustomerAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class LoginCustomerAuthService {
    @Autowired
    private CustomerAuthRepository customerAuthRepository;
    public CustomerAuth loginCustomerAuth (String email, String password) throws Exception {
        List<CustomerAuth> customerAuths = customerAuthRepository.getAllCustomerAuth();
        byte[] decodedPasswordByte = Base64.getDecoder().decode(password);
        String decodedPassword = new String(decodedPasswordByte);
        CustomerAuth foundCustomerAuth = null;
        for (CustomerAuth customerAuth: customerAuths) {
            if (Objects.equals(customerAuth.getEmail(), email) && Objects.equals(customerAuth.getPassword(), decodedPassword)) {
                foundCustomerAuth = customerAuth;
            }
        };
        if (foundCustomerAuth == null) {
            throw new Exception("Incorrect Email or Password!");
        } else {
            return foundCustomerAuth;
        }
    }
}
