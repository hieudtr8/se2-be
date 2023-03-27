package main.controller.product;

import main.controller.Response;
import main.model.Product;
import main.service.product.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class CreateProductController {

    @Autowired
    private CreateProductService createProductService;

    @Validated
    static class CreateProductRequest {
        public String name;
        public Double price;
        public String description;
        public String[] images;
        public Integer amount;
        public String brand;
    }

    @PostMapping(value = "/product")
    public ResponseEntity<Response<?>> createProduct(
            @RequestBody CreateProductRequest request
    ) {
        try {
            Product product = createProductService.createProduct(
                    request.name,
                    request.price,
                    request.description,
                    request.images == null ? null : new ArrayList<>(Arrays.asList(request.images)),
                    request.amount,
                    request.brand
            );
            return new ResponseEntity<>(new Response<Product>(
                    "Product " + product.getName() + " created",
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
