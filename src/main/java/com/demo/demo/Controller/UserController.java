package com.demo.demo.Controller;

import com.demo.demo.Service.UserService;
import com.demo.demo.dtos.CandidateDTO;
import com.demo.demo.dtos.UserDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.User;
import org.mapstruct.control.MappingControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @GetMapping("/users")
    public List<UserDTo> getAllUser() {
        return userService.getAllUser();
    }


    @PutMapping("/{id}")

    public UserDTo updateUser(@PathVariable Long id, @RequestBody UserDTo userDTo) {
        return userService.updateUser(id, userDTo);
    }

    @DeleteMapping("/{id}")
    public void softDeleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
    }





}
