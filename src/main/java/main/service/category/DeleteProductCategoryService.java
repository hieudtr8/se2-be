package main.service.category;

import main.model.ProductCategory;
import main.repository.category.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public void deleteProductCategory(String productId, String categoryId) throws Exception {
        productCategoryRepository.deleteProductCategory(productId, categoryId);
    }
}
