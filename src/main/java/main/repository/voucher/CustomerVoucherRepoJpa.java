package main.repository.voucher;

import main.repository.voucher.model.CustomerVoucherRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerVoucherRepoJpa extends JpaRepository<CustomerVoucherRepoModel, String> {
}
