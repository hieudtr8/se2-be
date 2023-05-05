package main.controller.voucher;

import main.controller.Response;
import main.model.Voucher;
import main.service.customer_auth.SearchCustomerAuthService;
import main.service.voucher.ClaimVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ClaimVoucherController {
    @Autowired
    private ClaimVoucherService claimVoucherService;

    @Autowired
    private SearchCustomerAuthService searchCustomerAuthService;


    @PostMapping(value = "/voucher/{id}/claim")
    public ResponseEntity<Response<?>> claimVoucher(
            @NonNull @PathVariable String id,
            @NonNull @RequestHeader(value="Authorization") String auth
    ) {
        try {
            searchCustomerAuthService.getCustomerAuthById(UUID.fromString(auth));
            Voucher voucher = claimVoucherService.claimVoucher(
                    UUID.fromString(auth),
                    UUID.fromString(id)
            );
            return ResponseEntity.ok(new Response<Voucher>(
                    "Voucher " + voucher.getCode() + " claimed",
                    true,
                    voucher
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
