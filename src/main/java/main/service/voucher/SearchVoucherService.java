package main.service.voucher;

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

    public Voucher getVoucherByCode(UUID code) {
        Voucher voucher = voucherRepository.getVoucherByCode(code);
        if (voucher == null)
            return null;
        // TODO: check if customer have claimed this voucher
        if (voucher.getVisibility() == Visibility.PROTECTED)
            return null;
        return voucher;
    }

    public List<Voucher> listVouchers() {
        List<Voucher> vouchers = voucherRepository.getAllVouchers();
        // TODO: check if customer have claimed this voucher
        return vouchers.stream().filter(voucher -> voucher.getVisibility() != Visibility.PROTECTED).toList();
    }
}
