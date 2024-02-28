package com.demo.demo.dtos;

import com.demo.demo.entity.User;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
public class RoleDTo {
    private String role;

}
