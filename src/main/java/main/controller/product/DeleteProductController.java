package main.controller.product;

import main.controller.Response;
import main.service.product.DeleteProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteProductController {

    @Autowired
    private DeleteProductService deleteProductService;

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<Response<?>> deleteProduct(
            @NonNull @PathVariable String id
    ) {
        try {
            deleteProductService.deleteProductById(UUID.fromString(id));
            return ResponseEntity.ok(new Response<>(
                    "Product deleted",
                    true,
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<String>(
                    e.getMessage(),
                    false,
                    null
            ));
        }
    }
}
