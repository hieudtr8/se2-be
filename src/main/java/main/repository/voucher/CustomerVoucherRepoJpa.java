package main.repository.voucher;

import main.repository.voucher.model.CustomerVoucherRepoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerVoucherRepoJpa extends JpaRepository<CustomerVoucherRepoModel, String> {
    List<CustomerVoucherRepoModel> findByCustomerId(String customerId);
    List<CustomerVoucherRepoModel> findByCode(String code);
}
