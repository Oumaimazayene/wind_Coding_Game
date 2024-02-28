package com.demo.demo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.util.Date;
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
  @OneToOne
    private Compagnes compagnes;

}
