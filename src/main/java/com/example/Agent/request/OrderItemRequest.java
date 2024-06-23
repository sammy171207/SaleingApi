package com.example.Agent.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private int quantity;

}
