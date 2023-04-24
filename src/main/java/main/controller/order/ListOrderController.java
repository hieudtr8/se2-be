package main.controller.order;

import main.controller.Response;
import main.model.Order;
import main.service.order.SearchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ListOrderController {
    @Autowired
    private SearchOrderService searchOrderService;

    @Validated
    static class ListOrderRequest {
        public String customerId;
    }

    @GetMapping("/order")
    public ResponseEntity<Response<?>> listOrder(
            @RequestBody ListOrderRequest request
    ) {
        try {
            List<Order> orders = searchOrderService.getOrders(UUID.fromString(request.customerId));
            return ResponseEntity.ok(new Response<>("Found " + orders.size() + " orders", true, orders));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response<>(e.getMessage(), false, null));
        }
    }
}
