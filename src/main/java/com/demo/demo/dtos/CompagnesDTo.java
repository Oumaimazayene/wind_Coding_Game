package com.demo.demo.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class CompagnesDTo {
 private String CompagneName;
 private String experience;
 private String difficulty;
 private Integer qtsNumber ;
 private Integer score ;
 private Date CreatedAt;
}
