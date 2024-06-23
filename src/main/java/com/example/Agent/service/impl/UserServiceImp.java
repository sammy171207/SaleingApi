package com.example.Agent.service.impl;

import com.example.Agent.config.JwtProvider;
import com.example.Agent.modal.ROLE;
import com.example.Agent.modal.User;
import com.example.Agent.repository.UserRepository;
import com.example.Agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
         String email=jwtProvider.getEmailFromJwtToken(jwt);
        System.out.println("find"+email);
         User user=findUserByUsername(email);
         if(user==null){
             throw new Exception("User Not Found");
         }
         return user;
    }

    @Override
    public boolean isAdmin(User user) throws Exception {
        return user != null && user.getRole() == ROLE.ROLE_ADMIN;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("useremail not found");
        }
        return user;
    }

    @Override
    public User findUserByUsername(String username) throws Exception {
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new Exception("username not found");
        }
        return user;
    }
}
