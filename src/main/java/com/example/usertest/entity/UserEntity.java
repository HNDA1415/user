package com.example.usertest.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1961835390940789032L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="mobile_number")
    private String mobileNumber;
    @ManyToMany(fetch =FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns =
            @JoinColumn(name = "user_id",referencedColumnName = "id"),inverseJoinColumns =
            @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();


}
