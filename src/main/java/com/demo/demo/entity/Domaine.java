package com.demo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "domaine")
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long id;
    @Column(name = "Lang")
    public String lang;
    @Column(name = "version")

    public String version ;
    @Column(name = "name")
    public String name ;


}
