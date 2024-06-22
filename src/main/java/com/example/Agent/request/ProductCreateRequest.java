package com.example.Agent.request;

import lombok.Data;

import java.util.List;
@Data
public class ProductCreateRequest {

    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private List<String> images;
    private boolean availableInStock;
}
