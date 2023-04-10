package main.controller.discount;

import main.controller.Response;
import main.model.Discount;
import main.service.discount.SearchDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class GetDiscountByIdController {

    @Autowired
    SearchDiscountService searchDiscountService;

    @GetMapping("/discount/{id}")
    public ResponseEntity<Response<?>> getDiscountById(@PathVariable UUID id) {
        try {
            Discount discount = searchDiscountService.getDiscountById(id);
            return ResponseEntity.ok(Response.success("Here's what you were looking for my love", discount));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Response.fail("I apologize for not be able to find what you're looking for, my babe"));
        }
    }
}
