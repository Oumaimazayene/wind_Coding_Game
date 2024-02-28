package com.demo.demo.Controller;

import com.demo.demo.Service.DomaineSevice;
import com.demo.demo.dtos.DomaineDTo;
import com.demo.demo.entity.Domaine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domaines")
public class DomaineController {
    private  final DomaineSevice domaineSevice ;

    public DomaineController(DomaineSevice domaineSevice) {
        this.domaineSevice = domaineSevice;

    }

    @GetMapping("/{id}")
    public Domaine getDomaineById(@PathVariable Long id) {
        return domaineSevice.getDomaineById(id);
    }
    @GetMapping("/all")
    public List<DomaineDTo> getAllDomaines() {
        return domaineSevice.getAllDomaines();
    }

    @PostMapping("/add")
    public ResponseEntity<?> createDomaine(@RequestBody DomaineDTo domaineDTo) {
        return ResponseEntity.ok().body(domaineSevice.createDomaine(domaineDTo));
    }


    @PutMapping("/{id}")

    public DomaineDTo updateDomaine(@PathVariable Long id, @RequestBody DomaineDTo domaineDTo) {
        return domaineSevice.updateDomaine(id, domaineDTo);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        domaineSevice.deleteDomaine(id);
    }

    @DeleteMapping("/domaines")
    public void deleteAllCandidates() {
        domaineSevice.deleteAllDomaine();
    }

}
