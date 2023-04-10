package main.repository.voucher;

import jakarta.transaction.Transactional;
import main.model.Visibility;
import main.model.Voucher;
import main.repository.voucher.model.VoucherRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component("voucherRepository")
public class VoucherRepository {
    @Autowired
    private VoucherRepoJpa voucherRepoJpa;

    public void saveVoucher(Voucher voucher) {
        try {
            voucherRepoJpa.save(new VoucherRepoModel(
                    voucher.getCode().toString(),
                    voucher.getQuantity(),
                    voucher.getValue(),
                    voucher.getMinimumApplicablePrice(),
                    voucher.getExpiredAt(),
                    voucher.getVisibility().toString()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Voucher getVoucherByCode(UUID code) {
        return voucherRepoJpa.findByCode(code.toString()).map(this::mapVoucher).orElse(null);
    }

    public List<Voucher> getAllVouchers() {
        List<VoucherRepoModel> vouchers = voucherRepoJpa.findAll();
        return vouchers.stream().map(this::mapVoucher).toList();
    }

    public Voucher getVoucherByVisibility(String visibility) {
        return voucherRepoJpa.findByVisibility(visibility).map(this::mapVoucher).orElse(null);
    }

    @Transactional
    public void deleteVoucherByCode(UUID code) throws Exception {
        Long voucherDeleted = voucherRepoJpa.deleteByCode(code.toString());
        if (voucherDeleted == 0) {
            throw new Exception("Voucher not found");
        }
    }

    private Voucher mapVoucher(VoucherRepoModel voucherFromDatabase) {
        try {
            return new Voucher(
                    UUID.fromString(voucherFromDatabase.code),
                    voucherFromDatabase.quantity,
                    voucherFromDatabase.value,
                    voucherFromDatabase.minimumApplicablePrice,
                    voucherFromDatabase.expiredAt,
                    Visibility.valueOf(voucherFromDatabase.visibility)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
