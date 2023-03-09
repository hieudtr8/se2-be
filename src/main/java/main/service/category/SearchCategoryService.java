package main.service.category;

import main.model.Category;
import main.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.getAllCategories();
    }

    public Category getCategoryById(String id) {
        return categoryRepository.getCategoryById(UUID.fromString(id));
    }
}
