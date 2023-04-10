package main.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Discount {
    private UUID id;
    private UUID productId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryDate;
    private double discountAmount;

    public Discount(UUID productId, LocalDateTime expiryDate, double discountAmount) throws Exception {
        this(UUID.randomUUID(), productId, expiryDate, discountAmount);
    }

    public Discount(@NonNull UUID id, @NonNull UUID productId,@NonNull LocalDateTime expiryDate,double discountAmount)
            throws Exception {
        setId(id);
        setProductId(productId);
        setExpiryDate(expiryDate);
        setDiscountAmount(discountAmount);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setDiscountAmount(double discountAmount) throws Exception {
        validateDiscountAmount(discountAmount);
        this.discountAmount = discountAmount;
    }

    private void validateDiscountAmount(double discountAmount) throws Exception {
        if (discountAmount <= 0 && discountAmount >= 1) {
            throw new Exception("Invalid discount amount value, must be 0 < discount < 1");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}
