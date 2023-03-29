package main.repository.voucher.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CustomerVoucherCompositeKey implements Serializable {
    private String customerId;
    private String code;
}
