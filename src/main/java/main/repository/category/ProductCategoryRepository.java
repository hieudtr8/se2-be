package main.repository.category;

import main.model.Category;
import main.model.Product;
import main.model.ProductCategory;
import main.repository.category.model.ProductCategoryRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component("productCategoryRepository")
public class ProductCategoryRepository {

    @Autowired
    private ProductCategoryRepoJpa productCategoryRepoJpa;

    public void saveProductCategory(ProductCategory productCategory) {
        productCategoryRepoJpa.save(new ProductCategoryRepoModel(productCategory.getProductId().toString(),
                productCategory.getCategoryId().toString()));
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryRepoJpa.findAll().stream().map(this::mapProductCategory).toList();
    }

    public ProductCategory getProductCategory(String productId, String categoryId) {
        ProductCategory productCategory =
                productCategoryRepoJpa.findByProductIdAndCategoryId(productId,
                        categoryId).map(this::mapProductCategory).orElse(null);

        return productCategory;
    }

    public void deleteProductCategory(String productId, String categoryId) throws Exception {
        try {
            productCategoryRepoJpa.deleteByProductIdAndCategoryId(productId,
                    categoryId);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Product not assigned to category");
        }
    }

    public List<ProductCategory> getCategoriesOf(String productId) {
        return  productCategoryRepoJpa.findAllByProductId(productId)
                .stream()
                .map(this::mapProductCategory)
                .toList();
    }

    public List<ProductCategory> getProductsOf(String categoryId) {
        return  productCategoryRepoJpa.findAllByCategoryId(categoryId)
                .stream()
                .map(this::mapProductCategory)
                .toList();
    }

    private ProductCategory mapProductCategory(ProductCategoryRepoModel productCategoryRepoModel) {
        return new ProductCategory(UUID.fromString(productCategoryRepoModel.productId),UUID.fromString( productCategoryRepoModel.categoryId));
    }

}
