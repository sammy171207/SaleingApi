package com.example.Agent.response;

import com.example.Agent.modal.ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private ROLE role;
}
