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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ListVoucherController {
    @Autowired
    private SearchVoucherService searchVoucherService;

    @Autowired
    private SearchCustomerAuthService searchCustomerAuthService;

    public static class SearchVoucherRequest {
        public String customerId;
    }

    @GetMapping(value = "/voucher")
    public ResponseEntity<Response<?>> listVouchers(
            @NonNull @RequestBody SearchVoucherRequest requestBody
    ) throws Exception {
        try {
            searchCustomerAuthService.getCustomerAuthById(UUID.fromString(requestBody.customerId));
            List<Voucher> vouchers = searchVoucherService.listVouchers(UUID.fromString(requestBody.customerId));
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
