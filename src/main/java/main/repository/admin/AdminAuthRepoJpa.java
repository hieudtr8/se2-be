package main.repository.admin;

import main.repository.admin.model.AdminAuthRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAuthRepoJpa extends JpaRepository<AdminAuthRepoModel, String> {
}
