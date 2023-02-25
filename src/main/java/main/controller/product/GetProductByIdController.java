package main.controller.product;

import main.controller.Response;
import main.model.Product;
import main.service.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetProductByIdController {

    @Autowired
    private SearchProductService searchProductService;

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Response<?>> getProductById(
            @NonNull @PathVariable String id
    ) {
        try {
            Product product = searchProductService.getProductById(UUID.fromString(id));
            return new ResponseEntity<>(new Response<>(
                    "Found Product " + product.getName(),
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
