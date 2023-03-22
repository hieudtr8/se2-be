package main.repository.admin;

import main.model.AdminAuth;
import main.repository.admin.model.AdminAuthRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("adminAuthRepository")
public class AdminAuthRepository {

    @Autowired
    private AdminAuthRepoJpa adminAuthRepoJpa;
    public void authAdmin(String id,String password) throws Exception {
        AdminAuth adminAuth = adminAuthRepoJpa.findById(id).map(this::mapAdmin).orElse(null);

        if (adminAuth == null) {
            throw new Exception("Id not found");
        }

        if (!adminAuth.password.equals(password)) {
            throw new Exception("Wrong password");
        }
    }

    private AdminAuth mapAdmin(AdminAuthRepoModel adminAuthRepoModel) {
        return new AdminAuth(UUID.fromString(adminAuthRepoModel.id), adminAuthRepoModel.password);
    }
}
