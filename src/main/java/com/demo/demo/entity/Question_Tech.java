package com.demo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question_Tech")
@DiscriminatorValue("Technique")

public class Question_Tech extends  Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "domaine_id")
    private Domaine domain;

}
