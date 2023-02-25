package main.service.product;

import main.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProductService {

    @Autowired
    private ProductRepository productRepository;

    public void deleteProductById(UUID id) throws Exception {
        productRepository.deleteProductById(id);
    }
}
