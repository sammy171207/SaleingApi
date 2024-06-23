package com.example.Agent.controller;

import com.example.Agent.modal.Order;
import com.example.Agent.modal.User;
import com.example.Agent.request.OrderRequest;
import com.example.Agent.service.OrderService;
import com.example.Agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PutMapping("/order")
    public ResponseEntity<Order>createOrder(@RequestBody OrderRequest request, @RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Order order=orderService.createOrder(request,user);

        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestBody OrderRequest request,@RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders=orderService.getUserOrder(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
