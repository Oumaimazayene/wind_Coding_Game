package com.demo.Api_Compilateur.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemsController {
    @GetMapping
    public String getItems() {
        // Logic for handling GET /items
        return "Handling GET request for /items";
    }
}
