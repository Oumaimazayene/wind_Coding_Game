package com.demo.demo.dtos;

import com.demo.demo.entity.Domaine;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Question_Tech_DTo extends  QuestionDTo{

private Long domain_id;
}
