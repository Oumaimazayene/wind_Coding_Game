package com.demo.demo.Service;

import com.demo.demo.dtos.CandidateDTO;
import com.demo.demo.dtos.CompagnesDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Compagnes;

import java.util.List;

public interface CompagneService {
    Compagnes getCompagneById(Long id);
    List<CompagnesDTo> getAllCompagnes();
    CompagnesDTo  createCompagne(CompagnesDTo compagnesDTo);
    CompagnesDTo updateCompagne(Long id, CompagnesDTo compagnesDTo);
    void deleteCompagne(Long id);
    void  deleteAllCompagnes();
    //void Compagne_Technique(Long CompagneId, String domaine1, String domaine2, String domaine3);
}
