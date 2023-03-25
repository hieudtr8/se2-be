package main.controller.voucher;

import main.controller.Response;
import main.model.Voucher;
import main.service.voucher.SearchVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListVoucherController {
    @Autowired
    private SearchVoucherService searchVoucherService;

    @GetMapping(value = "/voucher")
    public ResponseEntity<Response<?>> listVouchers() {
        List<Voucher> vouchers = searchVoucherService.listVouchers();
        try {
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
