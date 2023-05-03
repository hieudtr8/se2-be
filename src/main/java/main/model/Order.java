package main.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private UUID customerId;
    private OrderStatus status;
    private Date date;
    private OrderPayment payment;
    private String address;
    private String name;
    private String phone;
    private String email;
    private List<OrderProduct> products;
    private Voucher voucherValue;

    public Order(String id, UUID customerId, OrderStatus status, Date date, OrderPayment payment, String address, String name, String phone, String email, List<OrderProduct> products, Voucher voucherValue) {
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

    public String getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public OrderPayment getPayment() {
        return payment;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public Voucher getVoucher() {
        return voucherValue;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPayment(OrderPayment payment) {
        this.payment = payment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public void setVoucher(Voucher voucherValue) {
        this.voucherValue = voucherValue;
    }



    private static void validateId(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception("Id must not be null or empty");
        }
        if (!id.matches("[0-9]+") || id.length() != 13) {
            throw new Exception("Id must contain number only and have length = 13");
        }
    }

}
