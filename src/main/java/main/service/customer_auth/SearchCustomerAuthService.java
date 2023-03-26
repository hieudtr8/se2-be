package main.service.customer_auth;

import main.model.CustomerAuth;
import main.repository.customer_auth.CustomerAuthRepoJpa;
import main.repository.customer_auth.CustomerAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class SearchCustomerAuthService {
    @Autowired
    private CustomerAuthRepository customerAuthRepository;
    @Autowired
    private CustomerAuthRepoJpa customerAuthRepoJpa;
    public CustomerAuth getCustomerAuthById(UUID id) throws Exception {
        CustomerAuth customerAuth = customerAuthRepository.getCustomerAuthById(id);
        if (customerAuth == null)
            throw new Exception("Customer with id " + id + " does not exist");
        return  customerAuth;
    }
    public List<CustomerAuth> getCustomerAuths () {
        return customerAuthRepository.getAllCustomerAuth();
    }
}
