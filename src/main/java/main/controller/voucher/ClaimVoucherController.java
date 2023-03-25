package main.controller.voucher;

import main.controller.Response;
import main.model.Voucher;
import main.service.voucher.ClaimVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClaimVoucherController {
    @Autowired
    private ClaimVoucherService claimVoucherService;

    public static class ClaimVoucherRequest {
        public String customerId;
    }

    @PostMapping(value = "/voucher/{id}/claim")
    public ResponseEntity<Response<?>> claimVoucher(
            @NonNull @PathVariable String id,
            @NonNull @RequestBody ClaimVoucherRequest request
    ) {
        try {
            Voucher voucher = claimVoucherService.claimVoucher(
                    UUID.fromString(request.customerId),
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
