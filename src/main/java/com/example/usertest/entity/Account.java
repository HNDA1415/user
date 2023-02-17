package com.example.usertest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double amount;
    @OneToOne
    private UserEntity user;

    public Account() {
    }

    public Account(Integer id, double amount) {
        this.id = id;
        this.amount = amount;
    }
}
