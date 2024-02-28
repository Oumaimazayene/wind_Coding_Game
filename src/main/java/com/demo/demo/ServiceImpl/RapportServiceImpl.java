package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.QuestionRepository;
import com.demo.demo.Repository.RapportRepository;
import com.demo.demo.Repository.SoumetRepository;
import com.demo.demo.Repository.TestRepository;
import com.demo.demo.Service.RapportService;

import com.demo.demo.Service.TestService;
import com.demo.demo.dtos.RapportDTo;
import com.demo.demo.entity.*;
import com.demo.demo.mappers.RapportMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RapportServiceImpl implements RapportService {
    private final RapportRepository rapportRepository;
    private final RapportMapper rapportMapper;
private final SoumetRepository soumetRepository;
private final QuestionRepository questionRepository;
private final TestRepository testRepository;
    public RapportServiceImpl(RapportRepository rapportRepository, RapportMapper rapportMapper, SoumetRepository soumetRepository, TestService testService, QuestionRepository questionRepository, TestRepository testRepository) {
        this.rapportRepository = rapportRepository;
        this.rapportMapper = rapportMapper;
        this.soumetRepository = soumetRepository;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
    }

    @Override
    public Rapport getRapportById(Long id) {
        return rapportRepository.findById(id).orElse(null);
    }

    @Override
    public List<RapportDTo> getAllRapports() {
        return rapportRepository.findAll().stream()
                .map(rapportMapper::ToRapportDTo)
                .collect(Collectors.toList());
    }

    @Override
    public RapportDTo createRapport(RapportDTo rapportDTo, List<String> reponsesCandidat) {

        Long soumetId = rapportDTo.getSoumet_id();
        Soumet soumet = soumetRepository.findById(soumetId).orElseThrow(() -> new RuntimeException("Relation non trouvée avec l'Id " + soumetId));

        Rapport rapport = rapportMapper.ToRapport(rapportDTo);
        Long testId = soumet.getTest().getId();
        rapport.setSoumet(soumet);

        // Enregistrez le rapport dans la base de données
        Rapport savedRapport = rapportRepository.save(rapport);

        // Appelez la méthode genererRapport pour mettre à jour les détails du rapport
        genererRapport(testId, reponsesCandidat, savedRapport);

        return rapportMapper.ToRapportDTo(savedRapport);
    }





    @Override
    public RapportDTo updateRapport(Long id, RapportDTo rapportDTo) {
        Optional<Rapport> existingRapport = rapportRepository.findById(id);
        if (existingRapport.isPresent()) {
            rapportMapper.updateRapportFromDTO(rapportDTo, existingRapport.get());
            return rapportMapper.ToRapportDTo(rapportRepository.save(existingRapport.get()));
        }
        return null;
    }

    @Override
    public void deleteRapport(Long id) {
        try {
            rapportRepository.deleteById(id);
            System.out.println("Rapport deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Rapport with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting Rapport ");
            throw e;
        }
    }

    @Override
    public void deleteAllRaports() {
        try {
            rapportRepository.deleteAll();
            System.out.println("All Rapports deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all Rapports");
            throw e;
        }

    }



@Override

     public boolean verifierReponse(Long questionId, String reponseCandidat) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);

        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            System.out.println("la question a vereifier  est "+ question);

            for (Answer answer : question.getAnswer()) {
                System.out.println("IsTrue : " + answer.getIsTrue());
                System.out.println("Réponse de la base de données : [" + answer.getAnswer() + "]");
                System.out.println("Réponse du candidat : [" + reponseCandidat + "]");

                if (answer.getIsTrue() && areEqualIgnoreCaseAndTrim(answer.getAnswer(), reponseCandidat)) {
                    System.out.println("La réponse est correcte!");
                    return true;
                }
            }
        }

        return false;
    }

    private boolean areEqualIgnoreCaseAndTrim(String str1, String str2) {
        return str1 != null && str2 != null && str1.trim().equalsIgnoreCase(str2.trim());
    }


    private RapportDTo genererRapport(Long testId, List<String> reponsesCandidat, Rapport rapport) {
        rapport.setDate(new Date());
Test test =  testRepository.findById(testId).orElseThrow(() -> new RuntimeException("test  non trouvé avec l'id " + testId));


        int qtsNumber = test.getCompagnes().getQtsNumber();
        if (reponsesCandidat.size() != qtsNumber) {
            throw new IllegalArgumentException("Le nombre de réponses de candidat ne correspond pas au nombre total de questions.");
        }

        int correctAnswerCount = 0;
        int totalScore = 0;

        List<Question> questions = test.getCompagnes().getQuestions();

        for (int i = 0; i < qtsNumber; i++) {
            Question question = questions.get(i);
            String reponseCandidat = reponsesCandidat.get(i);

            if (verifierReponse(question.getId(), reponseCandidat)) {
                for (Answer answer : question.getAnswer()) {
                    if (answer.getIsTrue() && reponseCandidat.equals(answer.getAnswer())) {
                        correctAnswerCount++;
                        totalScore += answer.getQuestion().getScore();
                        break;
                    }
                }
            }
        }

        rapport.setCorrectAnswerCount(correctAnswerCount);
        rapport.setQtsNumber(qtsNumber);
        rapport.setScore(totalScore);

        double successRate = (double) correctAnswerCount / qtsNumber * 100;
        rapport.setSuccessRate(successRate);

        Rapport savedRapport = rapportRepository.save(rapport);
        return rapportMapper.ToRapportDTo(savedRapport);
    }






}

