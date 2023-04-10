package main.controller.voucher;

import main.controller.Response;
import main.model.Voucher;
import main.service.voucher.EditVoucherService;
import main.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class EditVoucherController {
    @Autowired
    private EditVoucherService editVoucherService;

    static class EditVoucherRequest {
        public Integer quantity;
        public Double value;
        public Double minimumApplicablePrice;
        public String expiredAt;
    }

    @PutMapping(value = "/voucher/{code}")
    public ResponseEntity<Response<?>> editVoucher(
            @PathVariable @NonNull String code,
            @NonNull @RequestBody EditVoucherRequest request
    ) {
        try {
            Voucher voucher = editVoucherService.editVoucher(
                    UUID.fromString(code),
                    new EditVoucherService.VoucherData(
                            request.quantity,
                            request.value,
                            request.minimumApplicablePrice,
                            request.expiredAt != null ? DateFormatter.parseDate(request.expiredAt) : null
                    )
            );
            return ResponseEntity.ok(new Response<Voucher>(
                    "Voucher " + voucher.getCode() + " edited",
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
