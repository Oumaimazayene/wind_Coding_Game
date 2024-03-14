package com.demo.demo.dtos;

import com.demo.demo.entity.Test;
import lombok.Data;

import java.util.List;

@Data
public class CandidateDTO {

    public String firstName ;
    public String lastName ;
    public String email ;
    private List<Test> tests;

}
