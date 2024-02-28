package com.demo.demo.Controller;

import com.demo.demo.Service.CandidateService;
import com.demo.demo.dtos.CandidateDTO;
import com.demo.demo.dtos.TypeDto;
import com.demo.demo.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/{id}")
    public Candidate getCandidateByIdd(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }


    @GetMapping("/all")
    public List<CandidateDTO> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @PostMapping("/add")
    public ResponseEntity<?> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        return ResponseEntity.ok().body(candidateService.createCandidate(candidateDTO));
    }


    @PutMapping("/{id}")

    public CandidateDTO updateCandidate(@PathVariable Long id, @RequestBody CandidateDTO candidateDTO) {
        return candidateService.updateCandidate(id, candidateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }

    @DeleteMapping("/candidates")
    public void deleteAllCandidates() {
        candidateService.deleteAllCandidates();
    }

}
