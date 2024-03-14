package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.QuestionRepository;
import com.demo.demo.Repository.RapportRepository;
import com.demo.demo.Repository.SoumetRepository;
import com.demo.demo.Repository.TestRepository;
import com.demo.demo.Service.RapportService;

import com.demo.demo.Service.TestService;
import com.demo.demo.dtos.CandidateReponseDTo;
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
    public RapportDTo createRapport(RapportDTo rapportDTo, List<CandidateReponseDTo> candidateReponseDTos) {

        Long soumetId = rapportDTo.getSoumet_id();
        Soumet soumet = soumetRepository.findById(soumetId).orElseThrow(() -> new RuntimeException("Relation non trouvée avec l'Id " + soumetId));

        Rapport rapport = rapportMapper.ToRapport(rapportDTo);
        Long testId = soumet.getTest().getId();
        rapport.setSoumet(soumet);
        Rapport savedRapport = rapportRepository.save(rapport);

      // genererRapport(testId, candidateReponseDTos, savedRapport);
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
    public boolean verifierReponse(Long questionId,CandidateReponseDTo candidateReponseDTO) {
        if (!questionId.equals(candidateReponseDTO.getIdQuestion())) {
            return false;
        }
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            System.out.println("La question à vérifier est " + question);

            List<String> candidateResponses = candidateReponseDTO.getReponses();

            for (Answer answer : question.getAnswer()) {
                System.out.println("IsTrue : " + answer.getIsTrue());
                System.out.println("Réponse de la base de données : [" + answer.getAnswer() + "]");

                // Vérifier si la réponse du candidat est parmi les réponses possibles
                if (answer.getIsTrue() && containsIgnoreCaseAndTrim(candidateResponses, answer.getAnswer())) {
                    System.out.println("La réponse est correcte!");
                    return true;
                }
            }
        }

        return false;
    }
    private boolean containsIgnoreCaseAndTrim(List<String> list, String value) {
        for (String item : list) {
            if (item.trim().equalsIgnoreCase(value.trim())) {
                return true;
            }
        }
        return false;
    }



  /*  private RapportDTo genererRapport(Long testId, List<CandidateReponseDTo> candidateReponseDTos, Rapport rapport) {
        rapport.setDate(new Date());
        Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test non trouvé avec l'id " + testId));

        int correctAnswerCount = 0;
        int totalScore = 0;
        int qtsNumber = test.getCompagnes().getQtsNumber();

        List<Question> questions = test.getCompagnes().getQuestions();

        for (int i = 0; i < qtsNumber; i++) {
            Question question = questions.get(i);
            CandidateReponseDTo candidateReponseDTO = candidateReponseDTos.get(i);

            if (verifierReponse(question.getId(), candidateReponseDTO)) {
                for (Answer answer : question.getAnswer()) {
                    if (answer.getIsTrue() && candidateReponseDTO.getReponses().contains(answer.getAnswer())) {
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

/*    public boolean verifierReponse(Long questionId, CandidateReponseDTo candidateReponseDTO) {
        // Vérifier si l'ID de la question correspond à celui fourni dans le DTO
        if (!questionId.equals(candidateReponseDTO.getIdQuestion())) {
            return false;
        }

        // Récupérer la question de la base de données
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            System.out.println("La question à vérifier est " + question);

            // Vérifier si la question est de type technique
            if (question instanceof Question_Tech && "code".equals(question.getType())) {
                Question_Tech questionTech = (Question_Tech) question;

                Domaine domaine = questionTech.getDomain();
                String lang = domaine.getLang();
                String version = domaine.getVersion();
                String code = candidateReponseDTO.getReponses().get(0);
                ClientRunRequestBody requestBody = new ClientRunRequestBody(lang, version, code, null);

                boolean resultatCompilation = compilateurService.compilerCode(requestBody);

                return resultatCompilation;
            } else {
                List<String> candidateResponses = candidateReponseDTO.getReponses();
                for (Answer answer : question.getAnswer()) {
                    if (answer.getIsTrue() && containsIgnoreCaseAndTrim(candidateResponses, answer.getAnswer())) {
                        System.out.println("La réponse est correcte!");
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

}

