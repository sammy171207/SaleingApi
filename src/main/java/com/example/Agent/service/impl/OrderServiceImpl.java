package com.example.Agent.service.impl;

import com.example.Agent.modal.*;
import com.example.Agent.repository.*;
import com.example.Agent.request.OrderItemRequest;
import com.example.Agent.request.OrderRequest;
import com.example.Agent.service.CartService;
import com.example.Agent.service.OrderService;
import com.example.Agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest request, User user) throws Exception {
        // Save address
        Address shipAddress = request.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shipAddress);

        if (!user.getAddress().contains(savedAddress)) {
            user.getAddress().add(savedAddress);
            userRepository.save(user);
        }

        // Create order
        Order createOrder = new Order();
        createOrder.setUser(user);
        createOrder.setOrderDate(new Date());
        createOrder.setOrderStatus(OrderStatus.PENDING);
        createOrder.setDeliveryAddress(savedAddress);

        // Create order items
        List<OrderItem> orderItems = new ArrayList<>();
        Long totalAmount = 0L;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new Exception("Product not found with id " + itemRequest.getProductId()));
            Long itemTotalPrice = (long)product.getPrice() * itemRequest.getQuantity();
            totalAmount += itemTotalPrice;

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(createOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setTotalPrice(itemTotalPrice);

            orderItems.add(orderItem);
        }

        createOrder.setOrderItems(orderItems);
        createOrder.setTotalAmount(totalAmount);

        // Save order and order items
        Order savedOrder = orderRepository.save(createOrder);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);
            orderItemRepository.save(orderItem);
        }

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, OrderStatus orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public void calculOrder(Long orderId) throws Exception {

    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new Exception("Order not found with id " + orderId);
        }
        return optionalOrder.get();
    }

    @Override
    public void calculateOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        Long totalAmount = 0L;
        for (OrderItem item : order.getOrderItems()) {
            totalAmount += item.getTotalPrice();
        }
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
    }
}
