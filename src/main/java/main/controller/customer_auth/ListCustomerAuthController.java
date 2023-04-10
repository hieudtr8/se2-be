package main.controller.customer_auth;

import main.controller.Response;
import main.model.CustomerAuth;
import main.service.customer_auth.SearchCustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListCustomerAuthController {
    @Autowired
    private SearchCustomerAuthService searchCustomerAuthService;
    @GetMapping(value="customer-auths")
    public ResponseEntity<Response<?>> getCustomerAuths() {
        try {
            List<CustomerAuth> customerAuths = searchCustomerAuthService.getCustomerAuths();
            return new ResponseEntity<>(new Response<>(
                    "Found " + customerAuths.size() + " customer profiles",
                    true,
                    customerAuths
            ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<String>(
                    e.getMessage(),
                    false,
                    null
            ), HttpStatus.BAD_REQUEST);
        }
    }
}
