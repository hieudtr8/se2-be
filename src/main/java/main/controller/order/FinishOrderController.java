package main.controller.order;

import main.controller.Response;
import main.model.Order;
import main.service.order.FinishOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class FinishOrderController {
    @Autowired
    private FinishOrderService finishOrderService;

    @Validated
    static class FinishOrderRequest {
        public String id;
    }

    @PostMapping("/order/{id}/finish")
    public ResponseEntity<Response<?>> finishOrder(
            @PathVariable String id
    ) {
        try {
            Order order = finishOrderService.finishOrder(id);
            return ResponseEntity.ok(new Response<>("Order finished", true, order));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response<>(e.getMessage(), false, null));
        }
    }
}
