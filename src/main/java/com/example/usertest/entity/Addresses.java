package com.example.usertest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Addresses{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String city;
    private String zipCode;
    private String streetName;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

    public Addresses() {
    }

    public Addresses(Integer id, String country, String city, String zipCode, String streetName) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.streetName = streetName;
    }
}
