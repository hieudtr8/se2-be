package main.service.order;

import main.model.Order;
import main.model.OrderStatus;
import main.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FinishOrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order finishOrder(String id) {
        Order order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Order already delivered");
        }
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.saveOrder(order);
        return order;
    }
}
