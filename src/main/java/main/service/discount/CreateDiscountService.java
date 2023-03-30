package main.service.discount;

import main.model.Discount;
import main.repository.discount.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class CreateDiscountService {
    @Autowired
    DiscountRepository discountRepository;

    public Discount createDiscount(UUID productId, LocalDateTime expiryDate, double discountAmount) throws Exception {
        Discount discount = new Discount(productId, expiryDate, discountAmount);
        if (discountRepository.searchDiscountBy(productId.toString()) != null) {
            throw new Exception("Discount already exist");
        }
        discountRepository.saveDiscount(discount);
        return discount;
    }
}
