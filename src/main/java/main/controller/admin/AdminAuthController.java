package main.controller.admin;

import main.controller.Response;
import main.model.AdminAuth;
import main.service.admin.AuthAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminAuthController {

    @Autowired
    private AuthAdminService authAdminService;

    @Validated
    static class AdminAuthRequest{
        public String id;
        public String password;
    }

    @GetMapping("/admin")
    public ResponseEntity<Response<?>> authAdmin(@RequestBody AdminAuthRequest adminAuthRequest) {
        try {
            authAdminService.authAdmin(adminAuthRequest.id, adminAuthRequest.password);
            return ResponseEntity.ok(Response.success("Admin authenticated", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.fail(e.getMessage()));
        }
    }
}
