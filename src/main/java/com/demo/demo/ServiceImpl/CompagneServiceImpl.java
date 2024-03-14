package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.CompagnesRepository;
import com.demo.demo.Repository.QuestionRepository;
import com.demo.demo.Repository.Question_Logique_Repository;
import com.demo.demo.Repository.Question_Tech_Repository;
import com.demo.demo.Service.CompagneService;
import com.demo.demo.Service.Question_Logique_Service;
import com.demo.demo.dtos.CompagnesDTo;
import com.demo.demo.dtos.Question_Logique_DTo;
import com.demo.demo.entity.*;
import com.demo.demo.mappers.CompagnesMappers;
import com.demo.demo.mappers.Question_Logique_Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class CompagneServiceImpl implements CompagneService {
    private  final CompagnesRepository compagnesRepository;
    private final CompagnesMappers compagnesMappers;


    @Override
    public Compagnes getCompagneById(Long id) {
        return compagnesRepository.findById(id).orElse(null);
    }

    @Override
    public List<CompagnesDTo> getAllCompagnes() {
        return compagnesRepository.findAll().stream()
                .map(compagnesMappers::ToCompagnesDTo)
                .collect(Collectors.toList());
    }

    @Override
    public CompagnesDTo createCompagne(CompagnesDTo compagnesDTo) {
        return compagnesMappers.ToCompagnesDTo(compagnesRepository.save(compagnesMappers.ToCompagnes(compagnesDTo)));
    }


    @Override
    public CompagnesDTo updateCompagne(Long id, CompagnesDTo compagnesDTo) {
        Optional<Compagnes> existingCompagne = compagnesRepository.findById(id);
        if (existingCompagne.isPresent()) {
            compagnesMappers.updateCompagneFromDTO(compagnesDTo, existingCompagne.get());
            return compagnesMappers.ToCompagnesDTo(compagnesRepository.save(existingCompagne.get()));
        }
        return null;
    }

    @Override
    public void deleteCompagne(Long id) {
        try {
            compagnesRepository.deleteById(id);
            System.out.println("Compagne deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Compagne with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting Compagne");
            throw e;
        }
    }

    @Override
    public void deleteAllCompagnes() {
        try {
            compagnesRepository.deleteAll();
            System.out.println("All Compagnes deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all Compagnes");
            throw e;
        }
    }

    /*public void Compagne_Technique(Long CompagneId, String domaine1, String domaine2, String domaine3) {
        Compagnes compagnes = compagnesRepository.findById(CompagneId).orElse(null);
        if (compagnes != null) {
            Integer qtsNumber = compagnes.getQtsNumber();

            List<Question_Tech> questionsTechniques1 = questionTechRepository.findRandomByDomaine(domaine1, qtsNumber / 3);
            List<Question_Tech> questionsTechniques2 = questionTechRepository.findRandomByDomaine(domaine2, qtsNumber / 3);
            List<Question_Tech> questionsTechniques3 = questionTechRepository.findRandomByDomaine(domaine3, qtsNumber / 3);

            compagnes.getQuestions().addAll(questionsTechniques1);
            compagnes.getQuestions().addAll(questionsTechniques2);
            compagnes.getQuestions().addAll(questionsTechniques3);

            int totalScore = calculateTotalScore(questionsTechniques1) + calculateTotalScore(questionsTechniques2) + calculateTotalScore(questionsTechniques3);
            compagnes.setScore(totalScore);

            compagnesRepository.save(compagnes);
        }
    }*/
}







