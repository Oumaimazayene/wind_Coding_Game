package com.demo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")

    private String lastName;
@Column(name="email")
    private String email;
@Column
    private String password;
@Column
private Boolean isdeleted;
    @ManyToOne
    private Role role ;

}
