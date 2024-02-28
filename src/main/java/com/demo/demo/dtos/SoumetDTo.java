package com.demo.demo.dtos;

import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Test;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class SoumetDTo {
    private Date SumittedDate;
    private Long candidate_id ;
    private Long test_id;
}
