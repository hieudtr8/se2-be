package main.controller.customer_auth;

import main.controller.Response;
import main.model.CustomerAuth;
import main.service.customer_auth.EditCustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EditCustomerAuthController {
    @Autowired
    private EditCustomerAuthService editCustomerAuthService;

    static class EditCustomerAuthRequest {
        public String password;
    }

    @PutMapping(value = "/customer/edit/{id}")
    public ResponseEntity<Response<?>> editCustomerAuth(@PathVariable String id, @RequestBody EditCustomerAuthRequest editCustomerAuthRequest) {
        try {
            CustomerAuth customerAuth = editCustomerAuthService.editCustomerProfile(UUID.fromString(id),
                    new EditCustomerAuthService.CustomerAuthData(
                            editCustomerAuthRequest.password
                    ));
            return new ResponseEntity<>(new Response<>(
                    "Edited Customer Auth " + customerAuth.getCustomerProfile().getName(),
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
