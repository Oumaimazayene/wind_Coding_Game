package com.demo.Api_Compilateur.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public String getAllUsers() {
        // Logic for handling GET /users
        return "Handling GET request for /users";
    }

    @PostMapping
    public String createUser() {
        // Logic for handling POST /users
        return "Handling POST request for /users";
    }

    @GetMapping("/data")
    public String getUserData() {
        // Logic for handling GET /users/data
        return "Handling GET request for /users/data";
    }
}
