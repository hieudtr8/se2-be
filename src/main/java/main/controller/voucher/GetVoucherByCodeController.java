package main.controller.voucher;

import main.controller.Response;
import main.model.Voucher;
import main.service.voucher.SearchVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetVoucherByCodeController {
    @Autowired
    private SearchVoucherService searchVoucherService;

    @GetMapping(value = "/voucher/{code}")
    public ResponseEntity<Response<?>> getVoucherByCode(
            @NonNull @PathVariable String code
    ) {
        try {
            Voucher voucher = searchVoucherService.getVoucherByCode(UUID.fromString(code));
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
