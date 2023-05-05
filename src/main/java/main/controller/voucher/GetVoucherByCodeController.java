package main.controller.voucher;

import main.controller.Response;
import main.model.Voucher;
import main.service.customer_auth.SearchCustomerAuthService;
import main.service.voucher.SearchVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GetVoucherByCodeController {
    @Autowired
    private SearchVoucherService searchVoucherService;

    @Autowired
    private SearchCustomerAuthService searchCustomerAuthService;

    @GetMapping(value = "/voucher/{code}")
    public ResponseEntity<Response<?>> getVoucherByCode(
            @NonNull @PathVariable String code,
            @RequestHeader("Authorization") @NonNull String auth
    ) {
        try {
            searchCustomerAuthService.getCustomerAuthById(UUID.fromString(auth));
            Voucher voucher = searchVoucherService.getVoucherByCode(UUID.fromString(code), UUID.fromString(auth));
            if (voucher == null)
                throw new Exception("Voucher with code " + code + " not found");
            return ResponseEntity.ok(new Response<Voucher>(
                    "Found Voucher " + voucher.getCode(),
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
