package main.controller.order;

import main.controller.Response;
import main.model.*;
import main.service.order.CreateOrderService;
import main.service.product.SearchProductService;
import main.service.voucher.SearchVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class CreateOrderController {
    @Autowired
    private CreateOrderService createOrderService;

    @Autowired
    private SearchVoucherService searchVoucherService;

    @Validated
    static class CreateOrderRequest {
        public String id;
        public String customerId;
        public String date;
        public String payment;
        public String address;
        public String name;
        public String phone;
        public String email;
        public CreateOrderProductRequest[] products;
        public String voucherCode;
    }

    static class CreateOrderProductRequest {
        public String id;
        public int amount;
    }

    @PostMapping("/order")
    public ResponseEntity<Response<?>> createOrder(@RequestBody CreateOrderRequest request) throws Exception {
        try {
            Voucher voucher;
            if (request.voucherCode != null) {
                voucher = searchVoucherService.getVoucherByCode(UUID.fromString(request.voucherCode), UUID.fromString(request.customerId));
                if (voucher == null)
                    throw new Exception("Voucher does not exist");
            } else
                voucher = null;
            Order order = createOrderService.createOrder(
                    request.id,
                    UUID.fromString(request.customerId),
                    OrderPayment.valueOf(request.payment),
                    request.address,
                    request.name,
                    request.phone,
                    request.email,
                    Arrays.stream(request.products).map(product -> new OrderProduct(UUID.fromString(product.id), product.amount)).toList(),
                    voucher
            );
            return ResponseEntity.ok(new Response<>("Order created successfully", true, order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<>(e.getMessage(), false, null));
        }
    }
}
