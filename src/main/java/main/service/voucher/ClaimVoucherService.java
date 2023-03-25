package main.service.voucher;

import main.model.CustomerVoucher;
import main.model.Voucher;
import main.repository.voucher.CustomerVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
public class ClaimVoucherService {

    @Autowired
    private CustomerVoucherRepository customerVoucherRepository;

    @Autowired
    private SearchVoucherService searchVoucherService;

    public Voucher claimVoucher(UUID customerId, UUID code) throws Exception {
        Voucher voucher = searchVoucherService.getVoucherByCode(code);
        if (voucher == null) {
            throw new RuntimeException("Voucher not found");
        }
        CustomerVoucher customerVoucher = customerVoucherRepository.getCustomerVoucherByCustomerId(customerId);
        if (customerVoucher == null) {
            customerVoucher = new CustomerVoucher(customerId, Arrays.asList(code));
        } else {
            customerVoucher.addCode(code);
        }
        customerVoucherRepository.saveCustomerVoucher(customerVoucher);
        return voucher;
    }
}
