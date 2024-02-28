package com.demo.demo.Controller;

import com.demo.demo.Service.SoumetService;
import com.demo.demo.dtos.RapportDTo;
import com.demo.demo.dtos.SoumetDTo;
import com.demo.demo.entity.Rapport;
import com.demo.demo.entity.Soumet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testSoumet")
public class SoumetController {
    private final SoumetService soumetService;

    public SoumetController(SoumetService soumetService) {
        this.soumetService = soumetService;
    }

    @GetMapping("/{id}")
    public Soumet soumetService(@PathVariable Long id) {
        return soumetService.getsoumetTestById(id);
    }

    @GetMapping("/testSoumet")
    public List<SoumetDTo> getAll() {
        return soumetService.getAll();
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody SoumetDTo soumetDTo) {
        return ResponseEntity.ok().body(soumetService.create(soumetDTo));
    }


    @PutMapping("/{id}")
    public SoumetDTo update(@PathVariable Long id, @RequestBody SoumetDTo soumetDTo) {
        return soumetService.update(id, soumetDTo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        soumetService.delete(id);
    }

    @DeleteMapping("/testSoumet")
    public void deleteAll() {
        soumetService.deleteAll();
    }


}
