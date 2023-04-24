package main.repository.order;

import main.repository.order.model.OrderRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepoJpa extends JpaRepository<OrderRepoModel, String> {
    List<OrderRepoModel> findByCustomerId(String customerId);
}
