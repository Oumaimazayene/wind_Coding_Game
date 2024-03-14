package com.demo.demo.Controller;

import com.demo.demo.Service.CandidateService;
import com.demo.demo.Service.RapportService;
import com.demo.demo.Service.TestService;
import com.demo.demo.dtos.CandidateReponseDTo;
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

    @PostMapping("/add")
    public ResponseEntity<RapportDTo> createRapport(@RequestBody RapportDTo rapportDTo, @RequestBody List<CandidateReponseDTo> candidateReponseDTos) {
        RapportDTo createdRapport = rapportService.createRapport(rapportDTo, candidateReponseDTos);
        return ResponseEntity.ok(createdRapport);
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
    @PostMapping("/verifier-reponse")
    public ResponseEntity<Boolean> verifierReponse(@RequestParam Long questionId, @RequestBody CandidateReponseDTo candidateReponseDTO) {
        boolean isCorrect = rapportService.verifierReponse(questionId, candidateReponseDTO);// You can customize the response accordingly, e.g., return HTTP 200 for true and HTTP 404 for false
        return ResponseEntity.ok(isCorrect);
    }
}


