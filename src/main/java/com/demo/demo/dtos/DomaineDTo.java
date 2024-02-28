package com.demo.demo.dtos;

import com.demo.demo.entity.Question;
import com.demo.demo.entity.Question_Tech;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
public class DomaineDTo {
    public String lang;
    public String version ;
    public String name ;
}
