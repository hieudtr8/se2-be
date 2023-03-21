package main.service.category;

import main.model.Category;
import main.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(String name) throws Exception {
        Category category = new Category(name);
        if (categoryRepository.getCategoryByName(name) != null) {
            throw new Exception("Category name already exist");
        }
        categoryRepository.saveCategory(category);
        return category;
    }
}
