package main.controller.category;

import main.controller.Response;
import main.service.category.DeleteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteCategoryController {

    @Autowired
    private DeleteCategoryService deleteCategoryService;

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Response<?>> deleteCategory(@PathVariable String id) {
        try {
            deleteCategoryService.deleteCategory(id);
            return ResponseEntity.ok(Response.success("Category deleted", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.fail(e.getMessage()));
        }
    }
}
