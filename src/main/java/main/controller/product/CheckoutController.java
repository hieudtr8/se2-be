package main.controller.product;

import main.controller.Response;
import main.model.Product;
import main.service.product.CheckoutService;
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
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    static class CheckoutRequest {
        public int quantity;
    }

    @PostMapping(value = "/product/{id}/checkout")
    public ResponseEntity<Response<?>> checkinProduct(
            @NonNull @PathVariable String id,
            @NonNull @RequestBody CheckoutRequest request
    ) {
        try {
            Product product = checkoutService.checkoutProduct(UUID.fromString(id), request.quantity);
            return new ResponseEntity<>(new Response<>(
                    "Checked out " + request.quantity + " " + product.getName(),
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
