package com.demo.demo.dtos;

import com.demo.demo.entity.Compagnes;
import com.demo.demo.entity.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data

public class UserDTo {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isdeleted;
    private RoleDTo role ;
}
