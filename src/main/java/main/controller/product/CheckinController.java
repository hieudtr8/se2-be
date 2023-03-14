package main.controller.product;

import main.controller.Response;
import main.model.Product;
import main.service.product.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CheckinController {
    @Autowired
    private CheckinService checkinService;

    static class CheckinRequest {
        public int quantity;
    }

    @PostMapping(value = "/product/{id}/checkin")
    public ResponseEntity<Response<?>> checkinProduct(
            @NonNull @PathVariable String id,
            @NonNull @RequestBody CheckinRequest request
    ) {
        try {
            Product product = checkinService.checkinProduct(UUID.fromString(id), request.quantity);
            return new ResponseEntity<>(new Response<>(
                    "Checked in " + request.quantity + " " + product.getName(),
                    true,
                    product
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
