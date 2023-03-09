package main.controller.category;


import main.controller.Response;
import main.model.Category;
import main.service.category.CreateCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CreateCategoryController {
    @Autowired
    private CreateCategoryService createCategoryService;

    @Validated
    static class CreateCategoryRequest {
        public String name;
    }

    @PostMapping("/category")
    public ResponseEntity<Response<?>> createCategory(@RequestBody CreateCategoryRequest request) {
        try {
            Category category = createCategoryService.createCategory(request.name);
            return ResponseEntity.ok( Response.success("Category "+category.getName()+" created", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.fail(e.getMessage()));
        }
    }
}
