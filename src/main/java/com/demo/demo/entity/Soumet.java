package com.demo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name="soumet")
@Entity
public class Soumet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column
    public Date SumittedDate;
    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidate candidate;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
}
