package com.leovegas.digital.wallet.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Player implements IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "balance")
    private double balance;

}