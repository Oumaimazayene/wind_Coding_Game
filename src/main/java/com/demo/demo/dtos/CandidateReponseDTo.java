package com.demo.demo.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CandidateReponseDTo {
    private Long idQuestion;
    private List<String> reponses;
}
