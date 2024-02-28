package com.demo.demo.dtos;


import lombok.Data;

import java.util.List;


@Data
public class QuestionDTo {

    private String title;
    private String questionBody;
    private Integer score;
    private String difficulty;
    private Integer time;
    private Boolean isPrivate;
    private  Long type_id;
    private List<AnswerDTo> answer;

}
