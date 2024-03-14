package com.demo.demo.Service;

import com.demo.demo.dtos.CandidateReponseDTo;
import com.demo.demo.dtos.RapportDTo;
import com.demo.demo.entity.*;

import java.util.List;

public interface RapportService {
    Rapport getRapportById(Long id);
    List<RapportDTo> getAllRapports();
    RapportDTo createRapport(RapportDTo rapportDTo, List<CandidateReponseDTo> candidateReponseDTos);
    RapportDTo updateRapport(Long id, RapportDTo rapportDTo);
    void deleteRapport(Long id);
    void deleteAllRaports();
    boolean verifierReponse(Long questionId,CandidateReponseDTo candidateReponseDTO);

}
