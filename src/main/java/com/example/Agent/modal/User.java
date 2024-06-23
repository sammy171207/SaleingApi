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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String email;
    private String username;
    private String password;

    private ROLE role=ROLE.ROLE_SALESMAN;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> address;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactDetail contactDetail;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;


}
