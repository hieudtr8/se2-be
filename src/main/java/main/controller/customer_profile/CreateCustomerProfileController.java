package main.controller.customer_profile;

import main.controller.Response;
import main.model.CustomerProfile;
import main.service.customer_profile.CreateCustomerProfileService;
import main.service.product.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateCustomerProfileController {
    @Autowired
    private CreateCustomerProfileService createCustomerProfileService;
    static class CreateCustomerProfileRequest {
        public String name;
        public Integer age;
        public String phone;
        public String address;
        public String avatar;
    }

    @PostMapping(value= "/customer-profile")
    public ResponseEntity<Response<?>> createCustomerProfile (@RequestBody CreateCustomerProfileRequest request) {
        try {
            CustomerProfile customerProfile = createCustomerProfileService.createCustomerProfile(
                    request.name,
                    request.age,
                    request.phone,
                    request.address,
                    request.avatar
            );
            return new ResponseEntity<>(new Response<CustomerProfile>(
                    "Customer Profile" + customerProfile.getName() + "created",
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
