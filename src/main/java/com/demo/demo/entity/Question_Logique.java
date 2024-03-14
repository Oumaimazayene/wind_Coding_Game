package com.demo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
 @Entity
@Table(name = "question_logique")
@DiscriminatorValue("Logique")
public class Question_Logique extends  Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name = "URLimage")
    private String URLimage;

}
