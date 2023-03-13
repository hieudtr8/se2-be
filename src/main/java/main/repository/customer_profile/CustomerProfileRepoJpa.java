package main.repository.customer_profile;

import main.repository.customer_profile.model.CustomerProfileRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerProfileRepoJpa extends JpaRepository<CustomerProfileRepoModel, String> {
    Optional<CustomerProfileRepoModel> findByName(String name);
}
