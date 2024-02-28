package com.demo.demo.Controller;

import com.demo.demo.Service.CompagneService;
import com.demo.demo.dtos.CompagnesDTo;
import com.demo.demo.entity.Compagnes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compagnes")
public class CompagneController {
    private final CompagneService compagneService;


    public CompagneController(CompagneService compagneService) {
        this.compagneService = compagneService;
    }
    @GetMapping("/{id}")
    public Compagnes getCompagneById(@PathVariable Long id) {
        return compagneService.getCompagneById(id);
    }


    @GetMapping("/compagnes")
    public List<CompagnesDTo> getAllCompagnes() {
        return compagneService.getAllCompagnes();
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCompagne(@RequestBody CompagnesDTo compagnesDTo) {
        return ResponseEntity.ok().body(compagneService.createCompagne(compagnesDTo));
    }


    @PutMapping("/{id}")

    public CompagnesDTo updateCompagne(@PathVariable Long id, @RequestBody CompagnesDTo compagnesDTo) {
        return compagneService.updateCompagne(id, compagnesDTo);
    }

    @DeleteMapping("/{id}")
    public void deleteCompagne(@PathVariable Long id) {
        compagneService.deleteCompagne(id);
    }

    @DeleteMapping("/compagnes")
    public void deleteAllCompagnes() {compagneService.deleteAllCompagnes();
    }
    @PostMapping("/{compagneId}/affecter-questions")
    public ResponseEntity<String> affecterQuestionsACompagnes(
            @PathVariable Long compagneId,
            @RequestBody List<Long> questionIds
    ) {
        try {
            compagneService.affecterQuestionsACompagnes(compagneId, questionIds);
            return ResponseEntity.ok("Questions affectées avec succès à la compagne.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
