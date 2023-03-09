package main.controller.category;

import main.controller.Response;
import main.model.Category;
import main.service.category.SearchCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ListCategoriesController {
    @Autowired
    private SearchCategoryService searchCategoryService;

    @GetMapping("/category")
    public ResponseEntity<Response<?>> getAllCategories() {
        List<Category> categories = searchCategoryService.getCategories();
        return ResponseEntity.ok(Response.success("Categories list", categories));
    }
}
