package com.example.Agent.repository;

import com.example.Agent.modal.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findByUserId(Long id);
}
