package main.service.voucher;

import main.model.Voucher;
import main.repository.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSearchVoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    public List<Voucher> listVouchers() {
        return voucherRepository.getAllVouchers();
    }
}
