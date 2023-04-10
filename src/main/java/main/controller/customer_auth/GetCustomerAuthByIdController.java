package main.controller.customer_auth;

import main.controller.Response;
import main.model.CustomerAuth;
import main.service.customer_auth.SearchCustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetCustomerAuthByIdController {
    @Autowired
    private SearchCustomerAuthService searchCustomerAuthService;
    @GetMapping(value = "/customer-auth/{id}")
    public ResponseEntity<Response<?>> getCustomerAuthById(
            @NonNull @PathVariable String id
    ) {
        try {
            CustomerAuth customerAuth = searchCustomerAuthService.getCustomerAuthById(UUID.fromString(id));
            return new ResponseEntity<>(new Response<>(
                    "Found Customer: " + customerAuth.getEmail(),
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
