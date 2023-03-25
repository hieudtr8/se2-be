package main.service.product;

import main.model.Product;
import main.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EditProductService {

    @Autowired
    private ProductRepository productRepository;

    public static class ProductData {
        String name;
        Double price;
        String description;
        List<String> images;
        String brand;

        public ProductData(String name, Double price, String description, List<String> images, String brand) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.images = images;
            this.brand = brand;
        }
    }

    public Product editProduct(UUID id, ProductData productData) throws Exception {
        Product product = productRepository.getProductById(id);

        if (product == null) {
            throw new Exception("Product not found");
        }
        if (productData.name != null) {
            if (productRepository.getProductByName(productData.name) != null && !product.getName().equals(productData.name))
                throw new Exception("Product " + productData.name + " already exists");
            product.setName(productData.name);
        }
        if (productData.price != null)
            product.setPrice(productData.price);
        if (productData.description != null)
            product.setDescription(productData.description);
        if (productData.images != null)
            product.setImages(productData.images);
        if (productData.brand != null)
            product.setBrand(productData.brand);
        productRepository.saveProduct(product);
        return product;
    }
}
