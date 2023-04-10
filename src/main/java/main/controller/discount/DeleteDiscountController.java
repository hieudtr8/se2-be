package main.controller.discount;

import main.controller.Response;
import main.service.discount.DeleteDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@Transactional
public class DeleteDiscountController {
    @Autowired
    DeleteDiscountService deleteDiscountService;

    @DeleteMapping("/discount/{id}")
    public ResponseEntity<Response<?>> deleteDiscountById(@PathVariable UUID id) {
        try {
            deleteDiscountService.deleteDiscountById(id);
            return ResponseEntity.ok(Response.success("I deleted it for you sweetie", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Response.fail("The id you provided might not exist, try something else"));
        }
    }
}
