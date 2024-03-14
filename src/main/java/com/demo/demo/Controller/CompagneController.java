package com.demo.demo.Controller;

import com.demo.demo.Service.CompagneService;
import com.demo.demo.dtos.CompagnesDTo;
import com.demo.demo.entity.Compagnes;
import org.springframework.http.HttpStatus;
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
    /*@PostMapping("compagneLogique/{compagneId}")
    public ResponseEntity<String> compagneLogique(@PathVariable Long compagneId) {
        try {
            compagneService.Compagne_Logique(compagneId);
            return ResponseEntity.ok("Compagne logique traitée avec succès.");
        } catch (Exception e) {
            e.printStackTrace(); // Vous pouvez personnaliser la gestion des erreurs en fonction de vos besoins
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement de la compagne logique.");
        }
    }*/

  /*  @PostMapping("/technique/{compagneId}/{domaine1}/{domaine2}/{domaine3}")
    public void createCompagneTechnique(
            @PathVariable Long compagneId,
            @PathVariable String domaine1,
            @PathVariable String domaine2,
            @PathVariable String domaine3
    ) {
        compagneService.Compagne_Technique(compagneId, domaine1, domaine2, domaine3);
    }*/

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
   /* @PostMapping("/{compagneId}/affecter-questions")
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
    }*/




}
