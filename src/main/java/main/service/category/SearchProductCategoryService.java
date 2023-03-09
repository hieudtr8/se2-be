package main.service.category;

import main.model.ProductCategory;
import main.repository.category.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAllCategoriesOf(String productId) {
        return productCategoryRepository.getCategoriesOf(productId);
    }
    public List<ProductCategory> getAllProductsOf(String categoryId) {
        return productCategoryRepository.getProductsOf(categoryId);
    }
}
