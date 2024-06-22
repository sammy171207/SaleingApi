package com.example.Agent.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private int stockQuantity;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;
    @Column(nullable = false)
    private boolean availableInStock;





}
