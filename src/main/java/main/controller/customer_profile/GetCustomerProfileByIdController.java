package main.controller.customer_profile;

import main.controller.Response;
import main.model.CustomerProfile;
import main.service.customer_profile.SearchCustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetCustomerProfileByIdController {
    @Autowired
    private SearchCustomerProfileService searchCustomerProfileService;
    @GetMapping(value = "/customer-profile/{id}")
    public ResponseEntity<Response<?>> getCustomerProfileById (@NonNull @PathVariable String id) {
        try {
            CustomerProfile customerProfile = searchCustomerProfileService.getCustomerProfileById(UUID.fromString(id));
            return new ResponseEntity<>(new Response<>(
                    "Found Customer Profile " + customerProfile.getName(),
                    true,
                    customerProfile
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
