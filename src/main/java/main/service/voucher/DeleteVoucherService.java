package main.service.voucher;

import main.repository.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteVoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    public void deleteVoucherByCode(UUID code) throws Exception {
        voucherRepository.deleteVoucherByCode(code);
    }
}
