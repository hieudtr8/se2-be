package main.repository.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
public class OrderRepoModel {
    @Column(nullable = false, length = 100)
    @Id
    public String id;
    public String customerId;
    public String status;
    public String date;
    public String payment;
    public String address;
    public String name;
    public String phone;
    public String email;
    public String products;
    public String voucherValue;

    public OrderRepoModel(
            String id,
            String customerId,
            String status,
            String date,
            String payment,
            String address,
            String name,
            String phone,
            String email,
            String products,
            String voucherValue
    ) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.date = date;
        this.payment = payment;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.products = products;
        this.voucherValue = voucherValue;
    }

    public OrderRepoModel() {

    }
}
