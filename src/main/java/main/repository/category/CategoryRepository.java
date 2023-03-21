package main.repository.category;

import main.model.Category;
import main.model.Product;
import main.model.ProductCategory;
import main.repository.category.model.CategoryRepoModel;
import main.repository.category.model.ProductCategoryRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component("categoryRepository")
public class CategoryRepository {
    @Autowired
    private CategoryRepoJpa categoryRepoJpa;
    public void saveCategory(Category category) {
        categoryRepoJpa.save(new CategoryRepoModel(category.getId().toString(), category.getName()));
    }

    public List<Category> getAllCategories() {
        return categoryRepoJpa.findAll().stream().map(this::mapCategory).toList();
    }

    public Category getCategoryById(UUID id) {
        Category category = categoryRepoJpa.findById(id.toString()).map(this::mapCategory).orElse(null);
        return category;
    }

    public Category getCategoryByName(String name) {
        return categoryRepoJpa.findByName(name).map(this::mapCategory).orElse(null);
    }
    public void deleteCategory(UUID id) throws Exception {
        try {
            categoryRepoJpa.deleteById(id.toString());
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Id not found");
        }
    }

    private Category mapCategory(CategoryRepoModel categoryRepoModel) {
        return new Category(UUID.fromString(categoryRepoModel.id), categoryRepoModel.name);
    }
}
