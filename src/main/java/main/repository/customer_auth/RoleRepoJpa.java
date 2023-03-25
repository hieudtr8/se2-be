package main.repository.customer_auth;

import main.repository.customer_auth.model.ERole;
import main.repository.customer_auth.model.RoleRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepoJpa extends JpaRepository<RoleRepoModel, Long> {
    RoleRepoModel findByName(ERole name);
}
