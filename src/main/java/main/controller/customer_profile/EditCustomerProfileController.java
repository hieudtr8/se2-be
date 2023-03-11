package main.controller.customer_profile;

import main.controller.Response;
import main.model.CustomerProfile;
import main.service.customer_profile.EditCustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EditCustomerProfileController {
    @Autowired
    private EditCustomerProfileService editCustomerProfileService;

    static class EditCustomerProfileRequest {
        public String name;
        public Integer age;
        public String phone;
        public String address;
        public String avatar;
    }

    @PutMapping(value = "/customer-profile/{id}")
    public ResponseEntity<Response<?>> editCustomerProfile(@PathVariable String id, @RequestBody EditCustomerProfileRequest request) {
        try {
            CustomerProfile customerProfile = editCustomerProfileService.editCustomerProfile(UUID.fromString(id), new EditCustomerProfileService.CustomerProfileData(
                    request.name,
                    request.age,
                    request.phone,
                    request.address,
                    request.avatar
            ));
            return new ResponseEntity<>(new Response<>(
                    "Edited Customer Profile " + customerProfile.getName(),
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
