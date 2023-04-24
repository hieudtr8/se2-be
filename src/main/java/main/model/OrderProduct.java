package main.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

public class OrderProduct {
    public UUID id;
    public int amount;

    public OrderProduct() {
    }

    public OrderProduct(UUID id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
