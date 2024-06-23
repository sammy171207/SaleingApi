package com.example.Agent.controller;

import com.example.Agent.modal.Product;
import com.example.Agent.modal.User;
import com.example.Agent.service.ProductService;
import com.example.Agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Product>>searchFood(@RequestParam String name, @RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Product>products=productService.searchProductByName(name);
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() throws Exception{
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
