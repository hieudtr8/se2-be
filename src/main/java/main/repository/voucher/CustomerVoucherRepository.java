package main.repository.voucher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.model.CustomerVoucher;
import main.repository.voucher.model.CustomerVoucherRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component("customerVoucherRepository")
public class CustomerVoucherRepository {
    @Autowired
    private CustomerVoucherRepoJpa customerVoucherRepoJpa;

    public CustomerVoucher saveCustomerVoucher(CustomerVoucher customerVoucher) {
        try {
            return mapCustomerVoucher(customerVoucherRepoJpa.save(new CustomerVoucherRepoModel(
                    customerVoucher.getCustomerId().toString(),
                    new ObjectMapper().writeValueAsString(customerVoucher.getCodes())
            )));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerVoucher getCustomerVoucherByCustomerId(UUID id) {
        return customerVoucherRepoJpa.findById(id.toString()).map(this::mapCustomerVoucher).orElse(null);
    }

    private CustomerVoucher mapCustomerVoucher(CustomerVoucherRepoModel customerVoucherRepoModel) {
        try {
            return new CustomerVoucher(
                    UUID.fromString(customerVoucherRepoModel.customerId),
                    new ObjectMapper().readValue(customerVoucherRepoModel.codes, new TypeReference<List<UUID>>() {})
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
