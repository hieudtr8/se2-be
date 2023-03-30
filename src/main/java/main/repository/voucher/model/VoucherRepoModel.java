package main.repository.voucher.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "voucher")
public class VoucherRepoModel {
    @Column(nullable = false, length = 100)
    @Id
    public String code;
    public int quantity;
    public double value;
    public double minimumApplicablePrice;
    public Date expiredAt;
    public String visibility;

    public VoucherRepoModel(
            String code,
            int quantity,
            double value,
            double minimumApplicablePrice,
            Date expiredAt,
            String visibility
    ) {
        this.code = code;
        this.quantity = quantity;
        this.value = value;
        this.minimumApplicablePrice = minimumApplicablePrice;
        this.expiredAt = expiredAt;
        this.visibility = visibility;
    }

    public VoucherRepoModel() {

    }
}
