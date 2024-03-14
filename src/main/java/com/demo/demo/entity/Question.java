package com.demo.demo.entity;

import com.demo.demo.dtos.AnswerDTo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "question_type", discriminatorType = DiscriminatorType.STRING)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "questionBody")
    private String questionBody;

    @Column(name = "score")
    private Integer score;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "time")
    private Integer time;

    @Column(name = "isPrivate")
    private Boolean isPrivate = false;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answer;
    @JsonBackReference
    @ManyToMany(mappedBy = "questions")
    private List<Test> tests;

}
