package main.controller.category;

import main.controller.Response;
import main.model.Category;
import main.service.category.SearchCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetCategoryByIdController {
    @Autowired
    private SearchCategoryService searchCategoryService;

    @GetMapping("/category/{id}")
    public ResponseEntity<Response<?>> getCategoryById(@PathVariable String id) {
        Category category = searchCategoryService.getCategoryById(id);
        return ResponseEntity.ok(Response.success("Success", category));
    }
}
