package main.controller.voucher;

import main.controller.Response;
import main.model.Visibility;
import main.model.Voucher;
import main.service.voucher.DeleteVoucherService;
import main.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class DeleteVoucherController {
    @Autowired
    private DeleteVoucherService deleteVoucherService;

    @DeleteMapping(value = "/voucher/{code}")
    public ResponseEntity<Response<?>> deleteVoucher(
            @PathVariable UUID code
    ) {
        try {
            deleteVoucherService.deleteVoucherByCode(code);
            return ResponseEntity.ok(new Response<String>(
                    "Voucher " + code + " deleted",
                    true,
                    null
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
