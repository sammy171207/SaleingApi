package com.example.Agent.service;

import com.example.Agent.modal.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;

    boolean isAdmin(User user)throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
