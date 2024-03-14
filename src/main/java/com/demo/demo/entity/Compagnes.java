package com.demo.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "compagnes")
public class Compagnes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "compagne_name")
    public String CompagneName;
    @Column(name = "experience")
    public String experience ;
    @Column(name = "difficulty")
    private String difficulty;
    @Column(name = "qtsNumber")
    public Integer qtsNumber ;
    @Column(name = "createdAt")
    public Date CreatedAt;
    @OneToMany(mappedBy = "campagne")
    private List<Test> tests;





}
