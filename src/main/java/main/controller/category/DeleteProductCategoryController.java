package main.controller.category;

import main.controller.Response;
import main.service.category.DeleteProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DeleteProductCategoryController {

    @Autowired
    private DeleteProductCategoryService deleteProductCategoryService;

    @DeleteMapping("/productCategory/")
    public ResponseEntity<Response<?>> deleteProductCategory(@RequestBody CreateProductCategoryController.CreateProductCategoryRequest request) {
        try {
            deleteProductCategoryService.deleteProductCategory(request.productId, request.categoryId);
            return ResponseEntity.ok(Response.success("Product " + request.productId + " unassigned to category " + request.categoryId,
                    null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.fail(e.getMessage()));
        }
    }
}
