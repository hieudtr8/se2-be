package main.controller.category;

import main.controller.Response;
import main.model.ProductCategory;
import main.service.category.SearchProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ListCategoriesOfProductController {

    @Autowired
    private SearchProductCategoryService searchProductCategoryService;

    @GetMapping("/{productId}/category")
    public ResponseEntity<Response<?>> listCategoriesOf(@PathVariable String productId) {
        List<ProductCategory> productCategories = searchProductCategoryService.getAllCategoriesOf(productId);
        return ResponseEntity.ok(Response.success("Here", productCategories));
    }
}
