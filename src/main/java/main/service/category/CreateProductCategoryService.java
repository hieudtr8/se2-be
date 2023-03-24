package main.service.category;

import main.model.Category;
import main.model.Product;
import main.model.ProductCategory;
import main.repository.category.CategoryRepository;
import main.repository.category.ProductCategoryRepository;
import main.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductCategory createProductCategory(String productId, String categoryId) throws Exception {
        Product product = productRepository.getProductById(UUID.fromString(productId));
        if (product == null) {
            throw new Exception("Product id not found");
        }
        Category category = categoryRepository.getCategoryById(UUID.fromString(categoryId));
        if (category == null) {
            throw new Exception("Category id not found");
        }

        ProductCategory productCategory = new ProductCategory(UUID.fromString(productId), UUID.fromString(categoryId));
        if (productCategoryRepository.getProductCategory(productId, categoryId) != null) {
            throw new Exception("Product already had this category");
        }
        productCategoryRepository.saveProductCategory(productCategory);
        return productCategory;
    }
}
