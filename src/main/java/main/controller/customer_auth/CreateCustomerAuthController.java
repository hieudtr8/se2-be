package main.controller.customer_auth;

import main.controller.Response;
import main.model.CustomerAuth;
import main.service.customer_auth.CreateCustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateCustomerAuthController {
    @Autowired
    private CreateCustomerAuthService createCustomerAuthService;
    @Validated
    static class createCustomerAuthRequest {
        public String email;
        public String password;
    }
    @PostMapping(value= "/customer/register")
    public ResponseEntity<Response<?>> createCustomerAuth (
            @RequestBody createCustomerAuthRequest request
    ) {
        try {
            CustomerAuth customerAuth = createCustomerAuthService.createCustomerAuth(
                    request.email,
                    request.password
            );
            return new ResponseEntity<>(new Response<CustomerAuth>(
                    "Customer Account: " + customerAuth.getEmail() + " created",
                    true,
                    customerAuth
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
