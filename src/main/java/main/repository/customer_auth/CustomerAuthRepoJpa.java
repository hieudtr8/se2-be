package main.repository.customer_auth;

import main.repository.customer_auth.model.CustomerAuthRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAuthRepoJpa extends JpaRepository<CustomerAuthRepoModel, String> {
    Optional<CustomerAuthRepoModel> findByEmail(String email);
    Boolean existsByEmail (String email);
}
