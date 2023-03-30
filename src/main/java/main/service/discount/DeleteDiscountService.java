package main.service.discount;

import main.repository.discount.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteDiscountService {
    @Autowired
    DiscountRepository discountRepository;

    public void deleteDiscountById(UUID id) throws Exception {
        discountRepository.deleteDiscount(id.toString());
    }
}
