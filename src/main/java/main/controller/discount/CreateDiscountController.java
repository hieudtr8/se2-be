package main.controller.discount;

import com.fasterxml.jackson.annotation.JsonFormat;
import main.controller.Response;
import main.model.Discount;
import main.service.discount.CreateDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Controller
public class CreateDiscountController {
    @Autowired
    CreateDiscountService createDiscountService;

    @Validated
    static class DiscountRequest {
        public String productId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        public LocalDateTime expiryDate;
        public double discountAmount;
    }

    @PostMapping("/discount")
    public ResponseEntity<Response<?>> createDiscount(@RequestBody DiscountRequest discountRequest) {
        try {
            Discount discount = createDiscountService.createDiscount(UUID.fromString(discountRequest.productId), discountRequest.expiryDate,
                    discountRequest.discountAmount);
            return ResponseEntity.ok(Response.success("Here's your discount darling", discount));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.fail("Honey, it looks like "+e.getMessage()));
        }
    }
}
