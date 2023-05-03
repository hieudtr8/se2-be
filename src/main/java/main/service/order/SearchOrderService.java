package main.service.order;


import main.model.Order;
import main.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchOrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order getOrderById(String id) {
        return orderRepository.getOrderById(id);
    }

    public List<Order> getOrders(UUID customerId) {
        return orderRepository.getOrders(customerId);
    }
}
