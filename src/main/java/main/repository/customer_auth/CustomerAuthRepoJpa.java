package main.repository.customer_auth;

import main.repository.customer_auth.model.CustomerAuthRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAuthRepoJpa extends JpaRepository<CustomerAuthRepoModel, String> {
    Optional<CustomerAuthRepoModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
