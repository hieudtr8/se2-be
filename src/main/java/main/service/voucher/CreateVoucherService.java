package main.service.voucher;

import main.model.Visibility;
import main.model.Voucher;
import main.repository.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateVoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    public Voucher createVoucher(
            int quantity,
            double value,
            double minimumApplicablePrice,
            Date expiredAt,
            Visibility visibility) throws Exception {
        Voucher voucher = new Voucher(quantity, value, minimumApplicablePrice, expiredAt, visibility);
        voucherRepository.saveVoucher(voucher);
        return voucher;
    }
}