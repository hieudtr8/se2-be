package main.service.voucher;

import main.model.CustomerVoucher;
import main.model.Voucher;
import main.repository.voucher.CustomerVoucherRepository;
import main.repository.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ClaimVoucherService {

    @Autowired
    private CustomerVoucherRepository customerVoucherRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    public Voucher claimVoucher(UUID customerId, UUID code) throws Exception {
        Voucher voucher = voucherRepository.getVoucherByCode(code);
        if (voucher == null) {
            throw new RuntimeException("Voucher not found");
        }
        customerVoucherRepository.saveCustomerVoucher(customerId, code);
        return voucher;
    }
}
