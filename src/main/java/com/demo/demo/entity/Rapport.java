package com.demo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rapport")
@Data
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column
    public Integer score ;
    @Column
    public Date date;
    @Column
    public Integer correctAnswerCount ;
    @Column
    public Double SuccessRate;
    @Column
    public Integer qtsNumber ;
    @OneToOne
    @JoinColumn(name = "soumet_id")
    private Soumet soumet;



}
