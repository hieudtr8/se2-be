package main.service.voucher;

import main.model.CustomerVoucher;
import main.model.Voucher;
import main.repository.voucher.CustomerVoucherRepository;
import main.repository.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<UUID> customersClaimedVoucher = customerVoucherRepository.getCustomerIdsByCode(code);
        if (customersClaimedVoucher.contains(customerId))
            throw new RuntimeException("You have already claimed this voucher");
        if (voucher.getQuantity() <= customersClaimedVoucher.size())
            throw new RuntimeException("Run out of voucher");
        if (voucher.getExpiredAt().before(new Date()))
            throw new RuntimeException("Voucher has expired");
        customerVoucherRepository.saveCustomerVoucher(customerId, code);
        return voucher;
    }
}
