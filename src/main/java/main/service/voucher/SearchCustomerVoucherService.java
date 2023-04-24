package main.service.voucher;

import main.model.CustomerVoucher;
import main.model.Voucher;
import main.repository.voucher.CustomerVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchCustomerVoucherService {
    @Autowired
    private CustomerVoucherRepository customerVoucherRepository;

    public CustomerVoucher getCustomerVoucher(UUID customerId) throws Exception {
        return customerVoucherRepository.getCustomerVoucherByCustomerId(customerId);
    }

    public List<UUID> getCustomerVoucherByCode(UUID code) throws Exception {
        return customerVoucherRepository.getCustomerIdsByCode(code);
    }
}
