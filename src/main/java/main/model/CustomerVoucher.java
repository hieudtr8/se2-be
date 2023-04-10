package main.model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CustomerVoucher {
    private final UUID customerId;
    private List<UUID> codes;

    public CustomerVoucher(UUID customerId, List<UUID> codes) throws Exception {
        this.customerId = customerId;
        setCodes(codes);
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<UUID> getCodes() {
        return Collections.unmodifiableList(codes);
    }

    public void setCodes(List<UUID> codes) throws Exception {
        validateCodes(codes);
        this.codes = codes;
    }

    public void addCode(UUID code) throws Exception {
        for (UUID c : codes) {
            if (c.equals(code)) {
                throw new Exception("Voucher has already been claimed");
            }
        }
        codes.add(code);
    }

    private static void validateCodes(List<UUID> codes) throws Exception {
        for (int i = 0; i < codes.size(); i++) {
            for (int j = i + 1; j < codes.size(); j++) {
                if (codes.get(i).equals(codes.get(j))) {
                    throw new Exception("Codes cannot be duplicated");
                }
            }
        }
        if (codes == null) {
            throw new Exception("Codes cannot be null");
        }
    }

    @Override
    public String toString() {
        return "CustomerVoucher{" +
                "customerId=" + customerId +
                ", codes=" + codes +
                '}';
    }
}
