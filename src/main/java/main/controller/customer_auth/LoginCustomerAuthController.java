package main.controller.customer_auth;

import main.controller.Response;
import main.model.CustomerAuth;
import main.service.customer_auth.LoginCustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginCustomerAuthController {
    @Autowired
    private LoginCustomerAuthService loginCustomerAuthService;
    static class LoginCustomerAuthRequest {
        public String email;
        public String password;
    }
    @PostMapping(value="/customer/login")
    public ResponseEntity<Response<?>> loginCustomerAuth(@RequestBody LoginCustomerAuthRequest request) {
        try {
            CustomerAuth customerAuth = loginCustomerAuthService.loginCustomerAuth(request.email, request.password);
            return new ResponseEntity<>(new Response<CustomerAuth>(
                    "Login successfully!",
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
