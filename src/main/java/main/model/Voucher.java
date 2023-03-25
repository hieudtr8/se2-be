package main.model;

import java.util.Date;
import java.util.UUID;

public class Voucher {
    private final UUID code;
    private int quantity;
    private double value;
    private double minimumApplicablePrice;
    private Date expiredAt;
    private Visibility visibility;

    public Voucher(UUID code, int quantity, double value, double minimumApplicablePrice, Date expiredAt, Visibility visibility) throws Exception {
        this.code = code;
        setQuantity(quantity);
        if (value > minimumApplicablePrice) {
            throw new Exception("Value must be less than or equal to minimum applicable price");
        }
        this.value = value;
        this.minimumApplicablePrice = minimumApplicablePrice;
        setExpiredAt(expiredAt);
        setVisibility(visibility);
    }

    public Voucher(int quantity, double value, double minimumApplicablePrice, Date expiredAt, Visibility visibility) throws Exception {
        this.code = UUID.randomUUID();
        setQuantity(quantity);
        if (value > minimumApplicablePrice) {
            throw new Exception("Value must be less than or equal to minimum applicable price");
        }
        this.value = value;
        this.minimumApplicablePrice = minimumApplicablePrice;
        setExpiredAt(expiredAt);
        setVisibility(visibility);
    }

    public UUID getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        return value;
    }

    public double getMinimumApplicablePrice() {
        return minimumApplicablePrice;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setQuantity(int quantity) throws Exception {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public void setValue(double value) throws Exception {
        validateValue(value);
        if (value > this.minimumApplicablePrice) {
            throw new Exception("Value must be less than or equal to minimum applicable price");
        }
        this.value = value;
    }

    public void setMinimumApplicablePrice(double minimumApplicablePrice) throws Exception {
        validateMinimumApplicablePrice(minimumApplicablePrice);
        if (minimumApplicablePrice < this.value) {
            throw new Exception("Minimum applicable price must be greater than or equal to value");
        }
        this.minimumApplicablePrice = minimumApplicablePrice;
    }

    public void setExpiredAt(Date expiredAt) throws Exception {
        validateExpiredAt(expiredAt);
        this.expiredAt = expiredAt;
    }

    public void setVisibility(Visibility visibility) throws Exception {
        validateVisibility(visibility);
        this.visibility = visibility;
    }

    public static void validateQuantity(int quantity) throws Exception {
        if (quantity < 0) {
            throw new Exception("Quantity must be greater than 0");
        }
    }

    public static void validateValue(double value) throws Exception {
        if (value < 0) {
            throw new Exception("Value must be greater than 0");
        }
    }

    public static void validateMinimumApplicablePrice(double minimumApplicablePrice) throws Exception {
        if (minimumApplicablePrice < 0) {
            throw new Exception("Minimum applicable price must be greater than 0");
        }
    }

    public static void validateExpiredAt(Date expiredAt) throws Exception {
        if (expiredAt.before(new Date())) {
            throw new Exception("Expired at must be greater than current date");
        }
    }

    public static void validateVisibility(Visibility visibility) throws Exception {
        if (visibility == null) {
            throw new IllegalArgumentException("Visibility must not be null");
        }
    }
}
