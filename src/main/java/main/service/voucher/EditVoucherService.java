package main.service.voucher;

import main.model.Visibility;
import main.model.Voucher;
import main.repository.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class EditVoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    public static class VoucherData {
        Integer quantity;
        Double value;
        Double minimumApplicablePrice;
        Date expiredAt;

        public VoucherData(Integer quantity, Double value, Double minimumApplicablePrice, Date expiredAt) {
            this.quantity = quantity;
            this.value = value;
            this.minimumApplicablePrice = minimumApplicablePrice;
            this.expiredAt = expiredAt;
        }
    }

    public Voucher editVoucher(UUID code, VoucherData voucherData) throws Exception {
        Voucher voucher = voucherRepository.getVoucherByCode(code);
        if (voucher == null) {
            throw new Exception("Voucher not found");
        }

        // TODO: check if voucher quantity to be edited is smaller than number of this voucher that has been claimed
        if (voucherData.quantity != null)
            voucher.setQuantity(voucherData.quantity);
        if (voucherData.value != null)
            voucher.setValue(voucherData.value);
        if (voucherData.minimumApplicablePrice != null)
            voucher.setMinimumApplicablePrice(voucherData.minimumApplicablePrice);
        if (voucherData.expiredAt != null)
            voucher.setExpiredAt(voucherData.expiredAt);
        voucherRepository.saveVoucher(voucher);
        return voucher;
    }
}
