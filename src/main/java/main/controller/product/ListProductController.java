package main.controller.product;

import main.controller.Response;
import main.model.Product;
import main.service.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class ListProductController {
    @Autowired
    private SearchProductService searchProductService;

    public enum Sorting {
        ASCENDING,
        DESCENDING
    }

    @GetMapping(value = "/product")
    public ResponseEntity<Response<?>> getProductById(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "sorting", required = false) String sorting,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        try {
            List<Product> products = searchProductService.getProducts();
            if (keyword != null) {
                products = products.stream().filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase())).toList();
            }
            if (brand != null && brand != "") {
                products = products.stream().filter(product -> product.getBrand().equalsIgnoreCase(brand)).toList();
            }
            if (sorting != null) {
                if (sorting.equalsIgnoreCase(Sorting.ASCENDING.toString())) {
                    products = products.stream().sorted(Comparator.comparing(Product::getPrice)).toList();
                } else if (sorting.equalsIgnoreCase(Sorting.DESCENDING.toString())) {
                    products = products.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).toList();
                }
            }
            if (page != null) {
                products = products.stream().skip((page - 1) * 10L).limit(10).toList();
            }
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
            ), HttpStatus.BAD_REQUEST);
        }
    }
}
