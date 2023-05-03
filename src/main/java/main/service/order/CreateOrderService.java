package main.service.order;

import main.model.*;
import main.repository.order.OrderRepository;
import main.service.product.SearchProductService;
import main.service.voucher.SearchCustomerVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CreateOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SearchOrderService searchOrderService;

    @Autowired
    private SearchProductService searchProductService;

    public Order createOrder(
            String id,
            UUID customerId,
            OrderPayment payment,
            String address,
            String name,
            String phone,
            String email,
            List<OrderProduct> products,
            Voucher voucher
    ) throws Exception {
        List<Order> oldOrders = searchOrderService.getOrders(customerId);
        Order order = new Order(id, customerId, OrderStatus.UNDELIVERED, new Date(), payment, address, name, phone, email, products, voucher);
        for (OrderProduct orderProduct: products) {
            int numberOfProductLeft = searchProductService.getProductById(orderProduct.id).getAmount();
            int numberOfVoucherLeft = voucher == null ? 0 : voucher.getQuantity();
            for (Order oldOrder: oldOrders) {
                for (OrderProduct oldOrderProduct: oldOrder.getProducts()) {
                    if (oldOrderProduct.id.equals(orderProduct.id)) {
                        numberOfProductLeft -= oldOrderProduct.amount;
                    }
                }
                if (oldOrder.getVoucher() != null && voucher != null) {
                    if (oldOrder.getVoucher().getCode().equals(voucher.getCode())) {
                        numberOfVoucherLeft -= 1;
                    }
                }
            }
            if (numberOfProductLeft < orderProduct.amount && voucher != null) {
                throw new Exception("Not enough product left");
            }
            if (numberOfVoucherLeft < 1 && voucher != null) {
                throw new Exception("Not enough voucher left");
            }
        }
        if (voucher != null) {
            if (voucher.getExpiredAt().before(new Date())) {
                throw new Exception("Voucher expired");
            }
        }

        orderRepository.saveOrder(order);
        return order;
    }
}
