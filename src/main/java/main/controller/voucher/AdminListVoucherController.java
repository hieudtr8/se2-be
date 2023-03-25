package main.controller.voucher;

import main.controller.Response;
import main.model.Voucher;
import main.service.voucher.AdminSearchVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminListVoucherController {
    @Autowired
    private AdminSearchVoucherService adminSearchVoucherService;

    @GetMapping(value = "/admin/voucher")
    public ResponseEntity<Response<?>> listVouchers() {
        try {
            List<Voucher> vouchers = adminSearchVoucherService.listVouchers();
            return ResponseEntity.ok(new Response<>(
                    "found " + vouchers.size() + " vouchers",
                    true,
                    vouchers
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response<String>(
                    e.getMessage(),
                    false,
                    null
            ));
        }
    }
}
