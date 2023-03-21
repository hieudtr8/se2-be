package main.service.admin;

import main.repository.admin.AdminAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    @Autowired
    private AdminAuthRepository adminAuthRepository;

    public void authAdmin(String id, String password) throws Exception {
        if (id == null || password == null) {
            throw new Exception("Id or password cannot be null");
        }
        adminAuthRepository.authAdmin(id, password);
    }
}
