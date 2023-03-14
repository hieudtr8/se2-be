package main.controller.product;

import main.controller.Response;
import main.model.Product;
import main.service.product.EditProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@RestController
public class EditProductController {

    @Autowired
    private EditProductService editProductService;

    static class EditProductRequest {
        public String name;
        public Double price;
        public String description;
        public String[] images;
        public String brand;
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<Response<?>> editProduct(
            @PathVariable String id,
            @RequestBody EditProductRequest request
    ) {
        try {
            Product product = editProductService.editProduct(UUID.fromString(id) , new EditProductService.ProductData(
                    request.name,
                    request.price,
                    request.description,
                    request.images == null ? null : new ArrayList<>(Arrays.asList(request.images)),
                    request.brand
            ));
            return new ResponseEntity<>(new Response<>(
                    "Edited Product " + product.getName(),
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
