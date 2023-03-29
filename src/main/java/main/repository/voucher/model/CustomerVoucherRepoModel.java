package main.repository.voucher.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_voucher")
@IdClass(CustomerVoucherCompositeKey.class)
public class CustomerVoucherRepoModel {

    @Column(nullable = false, length = 100)
    @Id
    public String customerId;

    @Column(nullable = false, length = 100)
    @Id
    public String code;

    public CustomerVoucherRepoModel(String customerId, String code) {
        this.customerId = customerId;
        this.code = code;
    }

    public CustomerVoucherRepoModel() {

    }

    @Override
    public String toString() {
        return "CustomerVoucherRepoModel{" +
                "customerId='" + customerId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
