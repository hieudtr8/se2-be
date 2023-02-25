package main.controller.product;

import main.controller.Response;
import main.model.Product;
import main.service.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListProductController {
    @Autowired
    private SearchProductService searchProductService;

    @GetMapping(value = "/product")
    public ResponseEntity<Response<?>> getProductById() {
        try {
            List<Product> products = searchProductService.getProducts();
            return new ResponseEntity<>(new Response<>(
                    "Found " + products.size() + " products",
                    true,
                    products
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
