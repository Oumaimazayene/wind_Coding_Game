package com.demo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Table(name = "Candidate")
@Entity
public class Candidate {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long id;
        @Column(name = "firstName")
        public String firstName ;
    @Column(name = "lastName")

    public String lastName ;
    @Column(name = "email")

    public String email ;
}
