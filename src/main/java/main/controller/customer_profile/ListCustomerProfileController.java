package main.controller.customer_profile;

import main.controller.Response;
import main.model.CustomerProfile;
import main.service.customer_profile.SearchCustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListCustomerProfileController {
    @Autowired
    private SearchCustomerProfileService searchCustomerProfileService;

    @GetMapping(value = "customer-profiles")
    public ResponseEntity<Response<?>> getCustomerProfiles() {
        try {
            List<CustomerProfile> customerProfiles = searchCustomerProfileService.getCustomerProfiles();
            return new ResponseEntity<>(new Response<>(
                    "Found " + customerProfiles.size() + " customer profiles",
                    true,
                    customerProfiles
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
