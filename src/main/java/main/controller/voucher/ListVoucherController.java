package main.controller.voucher;

import main.controller.Response;
import main.model.Voucher;
import main.service.customer_auth.SearchCustomerAuthService;
import main.service.voucher.SearchVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ListVoucherController {
    @Autowired
    private SearchVoucherService searchVoucherService;

    @Autowired
    private SearchCustomerAuthService searchCustomerAuthService;

    @GetMapping(value = "/voucher")
    public ResponseEntity<Response<?>> listVouchers(
            @NonNull @RequestHeader(value="Authorization") String auth
    ) throws Exception {
        try {
            searchCustomerAuthService.getCustomerAuthById(UUID.fromString(auth));
            List<Voucher> vouchers = searchVoucherService.listVouchers(UUID.fromString(auth));
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
