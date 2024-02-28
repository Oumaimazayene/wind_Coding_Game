package com.demo.demo.dtos;

import com.demo.demo.entity.Answer;
import com.demo.demo.entity.Question;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
public class AnswerDTo {
    private String answer;
    private Boolean isTrue;

}
