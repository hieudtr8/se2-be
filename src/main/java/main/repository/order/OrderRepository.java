package main.repository.order;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import main.model.*;
import main.repository.order.model.OrderRepoModel;
import main.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component("orderRepository")
public class OrderRepository {
    @Autowired
    private OrderRepoJpa orderRepoJpa;

    public void saveOrder(Order order) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            orderRepoJpa.save(new OrderRepoModel(
                    order.getId(),
                    order.getCustomerId().toString(),
                    order.getStatus().toString(),
                    DateFormatter.formatDate(order.getDate()),
                    order.getPayment().toString(),
                    order.getAddress(),
                    order.getName(),
                    order.getPhone(),
                    order.getEmail(),
                    mapper.writeValueAsString(order.getProducts()),
                    mapper.writeValueAsString(order.getVoucher())
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Order getOrderById(String id) {
        return orderRepoJpa.findById(id).map(this::mapOrder).orElse(null);
    }

    public List<Order> getOrders(UUID customerId) {
        List<OrderRepoModel> orders = orderRepoJpa.findByCustomerId(customerId.toString());
        return orders.stream().map(this::mapOrder).toList();
    }

    public List<Order> getAllOrders() {
        List<OrderRepoModel> orders = orderRepoJpa.findAll();
        return orders.stream().map(this::mapOrder).toList();
    }

    private Order mapOrder(OrderRepoModel orderFromDatabase) {
        try {
            List<OrderProduct> orderProduct = new ObjectMapper().readValue(orderFromDatabase.products, new TypeReference<>() {});
            Voucher voucher = new ObjectMapper().readValue(orderFromDatabase.voucherValue, Voucher.class);
            return new Order(
                    orderFromDatabase.id,
                    UUID.fromString(orderFromDatabase.customerId),
                    OrderStatus.valueOf(orderFromDatabase.status),
                    DateFormatter.parseDate(orderFromDatabase.date),
                    OrderPayment.valueOf(orderFromDatabase.payment),
                    orderFromDatabase.address,
                    orderFromDatabase.name,
                    orderFromDatabase.phone,
                    orderFromDatabase.email,
                    orderProduct,
                    voucher
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}