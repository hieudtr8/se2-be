package main.controller.category;

import main.controller.Response;
import main.model.ProductCategory;
import main.service.category.CreateProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CreateProductCategoryController {
    @Autowired
    private CreateProductCategoryService createProductCategoryService;

    @Validated
    static class ProductCategoryRequest {
        public String productId;
        public String categoryId;
    }

    @PostMapping("/productCategory")
    public ResponseEntity<Response<?>> createProductCategory(@RequestBody
                                                             ProductCategoryRequest request) {
        try {
            ProductCategory productCategory =
                    createProductCategoryService.createProductCategory(request.productId, request.categoryId);
            return ResponseEntity.ok( Response.success("Product assigned to category", productCategory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.fail(e.getMessage()));
        }
    }
}
