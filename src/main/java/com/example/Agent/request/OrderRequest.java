package com.example.Agent.request;

import com.example.Agent.modal.Address;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<OrderItemRequest> items;
    private Address deliveryAddress;
}
