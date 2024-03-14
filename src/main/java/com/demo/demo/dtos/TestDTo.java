package com.demo.demo.dtos;

import com.demo.demo.entity.Candidate;
import lombok.Data;

import java.util.Date;
@Data
public class TestDTo {
    public Date createdAt;
    public Boolean isSubmitted;
    private Long compagnes_id ;
    private Candidate candidate;


}



