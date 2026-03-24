package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    private String displayName;


}
