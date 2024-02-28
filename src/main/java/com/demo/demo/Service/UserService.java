package com.demo.demo.Service;

import com.demo.demo.dtos.CandidateDTO;
import com.demo.demo.dtos.UserDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.User;

import java.util.List;

public interface UserService {


    User getUserById(Long id);
    List<UserDTo> getAllUser();
    UserDTo updateUser(Long id, UserDTo userDTo);
    void softDeleteUser(Long id);
}
