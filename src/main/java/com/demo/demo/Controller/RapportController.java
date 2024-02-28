package com.demo.demo.Controller;

import com.demo.demo.Service.CandidateService;
import com.demo.demo.Service.RapportService;
import com.demo.demo.Service.TestService;
import com.demo.demo.dtos.RapportDTo;
import com.demo.demo.dtos.TestDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Rapport;
import com.demo.demo.entity.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rapports")
public class RapportController {
    private final RapportService rapportService;
    private final CandidateService candidateService; // Ajout du service de candidat
    private final TestService testService;


    public RapportController(RapportService rapportService, CandidateService candidateService, TestService testService) {
        this.rapportService = rapportService;
        this.candidateService = candidateService;
        this.testService = testService;
    }

    @GetMapping("/{id}")
    public Rapport getRapportById(@PathVariable Long id) {
        return rapportService.getRapportById(id);
    }

    @GetMapping("/tests")
    public List<RapportDTo> getAllRapports() {
        return rapportService.getAllRapports();
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<?> createRapport(@RequestBody RapportDTo rapportDTo,@RequestParam("reponsesCandidat") List<String> reponsesCandidat){

        RapportDTo createdRapport = rapportService.createRapport(rapportDTo,reponsesCandidat );

        return ResponseEntity.ok().body(createdRapport);
    }





    @PutMapping("/{id}")
    public RapportDTo updateRapport(@PathVariable Long id, @RequestBody RapportDTo rapportDTo) {
        return rapportService.updateRapport(id, rapportDTo);
    }

    @DeleteMapping("/{id}")
    public void deleteRapport(@PathVariable Long id) {
        rapportService.deleteRapport(id);
    }

    @DeleteMapping("/rapports")
    public void deleteAllRaports() {
        rapportService.deleteAllRaports();
    }
    @PostMapping("/verifierReponse")
    public ResponseEntity<Boolean> verifierReponse(@RequestParam Long questionId, @RequestBody String reponsesCandidat) {
        boolean result = rapportService.verifierReponse(questionId, reponsesCandidat);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}


