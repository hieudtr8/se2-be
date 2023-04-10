package main.repository.discount.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "discount")
public class DiscountRepoModel {
    @Id
    @Column(nullable = false, length = 100)
    public String id;

    @Column(nullable = false, length = 100)
    public String productId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime expiryDate;

    public double discountAmount;

    public DiscountRepoModel(String id, String productId, LocalDateTime expiryDate, double discountAmount) {
        this.id = id;
        this.productId = productId;
        this.expiryDate = expiryDate;
        this.discountAmount = discountAmount;
    }

    public DiscountRepoModel() {

    }
}
