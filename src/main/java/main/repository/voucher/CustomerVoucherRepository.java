package main.repository.voucher;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.model.CustomerVoucher;
import main.repository.voucher.model.CustomerVoucherRepoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component("customerVoucherRepository")
public class CustomerVoucherRepository {
    @Autowired
    private CustomerVoucherRepoJpa customerVoucherRepoJpa;

    public void saveCustomerVoucher(UUID customerId, UUID code) {
        customerVoucherRepoJpa.save(new CustomerVoucherRepoModel(customerId.toString(), code.toString()));
    }


    public CustomerVoucher getCustomerVoucherByCustomerId(UUID id) throws Exception {
        List<CustomerVoucherRepoModel> customerVoucherRepoModels = customerVoucherRepoJpa.findByCustomerId(id.toString());
        if (customerVoucherRepoModels.size() == 0)
            return null;
        return mapCustomerVoucher(customerVoucherRepoModels);
    }

    public List<UUID> getCustomerIdsByCode(UUID code) throws Exception {
        List<CustomerVoucherRepoModel> customerVoucherRepoModels = customerVoucherRepoJpa.findByCode(code.toString());
        if (customerVoucherRepoModels.size() == 0)
            return new ArrayList<>();
        return customerVoucherRepoModels.stream().map(customerVoucherRepoModel -> UUID.fromString(customerVoucherRepoModel.customerId)).toList();
    }

    private CustomerVoucher mapCustomerVoucher(List<CustomerVoucherRepoModel> customerVoucherRepoModels) throws Exception {
        if (customerVoucherRepoModels.size() == 0)
            return null;
        return new CustomerVoucher(
                UUID.fromString(customerVoucherRepoModels.get(0).customerId),
                new ArrayList<>(customerVoucherRepoModels.stream().map(customerVoucherRepoModel -> UUID.fromString(customerVoucherRepoModel.code)).toList())
        );
    }
}
