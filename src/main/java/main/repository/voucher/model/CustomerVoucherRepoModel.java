package main.repository.voucher.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_voucher")
public class CustomerVoucherRepoModel {
    @Column(nullable = false, length = 100)
    @Id
    public String customerId;
    public String codes;

    public CustomerVoucherRepoModel(String customerId, String codes) {
        this.customerId = customerId;
        this.codes = codes;
    }

    public CustomerVoucherRepoModel() {

    }
}
