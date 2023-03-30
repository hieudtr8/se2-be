package main.controller.voucher;

import main.controller.Response;
import main.model.Visibility;
import main.model.Voucher;
import main.service.voucher.CreateVoucherService;
import main.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CreateVoucherController {
    @Autowired
    private CreateVoucherService createVoucherService;

    @Validated
    static class CreateVoucherRequest {
        public int quantity;
        public double value;
        public double minimumApplicablePrice;
        public String expiredAt;
        public String visibility;
    }

    @PostMapping(value = "/voucher")
    public ResponseEntity<Response<?>> createVoucher(
            @RequestBody CreateVoucherRequest request
    ) {
        try {
            Visibility visibility;
            if (request.visibility.equals("public")) {
                visibility = Visibility.PUBLIC;
            } else if (request.visibility.equals("protected")) {
                visibility = Visibility.PROTECTED;
            } else {
                throw new Exception("Visibility must be public or protected");
            }

            Voucher voucher = createVoucherService.createVoucher(
                    request.quantity,
                    request.value,
                    request.minimumApplicablePrice,
                    DateFormatter.parseDate(request.expiredAt),
                    visibility
            );
            return ResponseEntity.ok(new Response<Voucher>(
                    "Voucher " + voucher.getCode() + " created",
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
