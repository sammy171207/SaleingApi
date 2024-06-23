package com.example.Agent.service;

import com.example.Agent.modal.Order;
import com.example.Agent.modal.OrderStatus;
import com.example.Agent.modal.User;
import com.example.Agent.request.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderService  {

    public Order createOrder(OrderRequest request, User user) throws Exception;

    public Order updateOrder(Long orderId, OrderStatus orderStatus) throws Exception;

    public void calculOrder(Long orderId) throws Exception;

    public List<Order>getUserOrder(Long userId) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;

    void calculateOrder(Long orderId) throws Exception;
}
