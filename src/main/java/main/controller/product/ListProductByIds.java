package main.controller.product;
import main.controller.Response;
import main.model.Product;
import main.service.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
public class ListProductByIds {
    @Autowired
    private SearchProductService searchProductService;

    static class IdsRequestBody {
        public String[] ids;
    }

    @PostMapping(value = "/product/ids")
    public ResponseEntity<Response<?>> getProductByIds(
            @RequestBody IdsRequestBody body
    ) {
        try {
            System.out.println(123);
            List<UUID> uuids = Stream.of(body.ids).map(UUID::fromString).toList();
            List<Product> products = searchProductService.getProductsByIds(uuids);
            return new ResponseEntity<>(new Response<>(
                    "Found " + products.size() + " products",
                    true,
                    products
            ), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response<String>(
                    e.getMessage(),
                    false,
                    null
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
