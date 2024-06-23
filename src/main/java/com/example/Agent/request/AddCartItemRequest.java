package com.example.Agent.request;

import lombok.Data;

@Data
public class AddCartItemRequest {
    private Long id;
    private int quantity;
}
