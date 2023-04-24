package main.service.voucher;

import main.model.CustomerVoucher;
import main.model.Visibility;
import main.model.Voucher;
import main.repository.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchVoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private SearchCustomerVoucherService searchCustomerVoucherService;

    public Voucher getVoucherByCode(UUID code, UUID customerId) throws Exception {
        Voucher voucher = voucherRepository.getVoucherByCode(code);
        if (voucher == null)
            return null;
        if (voucher.getVisibility() == Visibility.PROTECTED) {
            CustomerVoucher customerVoucher = searchCustomerVoucherService.getCustomerVoucher(customerId);
            if (customerVoucher == null)
                return null;
            if (customerVoucher.getCodes().contains(voucher.getCode()))
                return voucher;
            return null;
        }
        return voucher;
    }

    public List<Voucher> listVouchers(UUID customerId) throws Exception {
        List<Voucher> vouchers = voucherRepository.getAllVouchers();
        CustomerVoucher customerVoucher = searchCustomerVoucherService.getCustomerVoucher(customerId);
        return vouchers.stream().filter(voucher -> {
            if (voucher.getVisibility() == Visibility.PUBLIC)
                return true;
            else {
                if (customerVoucher == null)
                    return false;
                if (customerVoucher.getCodes().contains(voucher.getCode()))
                    return true;
                return false;
            }
        }).toList();
    }
}
