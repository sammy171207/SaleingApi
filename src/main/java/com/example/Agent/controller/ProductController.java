package com.example.Agent.controller;

import com.example.Agent.service.ProductService;
import com.example.Agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
}
