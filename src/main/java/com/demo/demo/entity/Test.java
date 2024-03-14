package com.demo.demo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "createdAt")
    public Date createdAt ;
    @Column(name = "isSubmitted")
    public Boolean isSubmitted;
    @Column
    public  Integer ScoreTotale;
    @Column
    public Integer correctAnswerCount ;
    @Column
    public Double SuccessRate;
    @Column
    public Integer scorefinale;
    @ManyToOne
    @JoinColumn(name = "campagne_id")
    private Compagnes campagne;
    @ManyToOne
    private Candidate candidate;
    @ManyToMany
    @JoinTable(
            name = "test_question",
            joinColumns = @JoinColumn(name = "compagne_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;
}
