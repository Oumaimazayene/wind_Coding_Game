package com.demo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.StyledEditorKit;

@Data
 @Entity
@Table(name = "question_logique")
@DiscriminatorValue("Logique")
public class Question_Logique extends  Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "URLimage")
    private String URLimage;

}
