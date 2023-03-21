package main.service.category;

import main.model.Category;
import main.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeleteCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void deleteCategory(String id) throws Exception {
        categoryRepository.deleteCategory(UUID.fromString(id));
    }
}
