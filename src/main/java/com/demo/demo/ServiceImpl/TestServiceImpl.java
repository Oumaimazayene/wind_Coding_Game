package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.CompagnesRepository;
import com.demo.demo.Repository.Question_Logique_Repository;
import com.demo.demo.Repository.TestRepository;
import com.demo.demo.Service.TestService;
import com.demo.demo.dtos.AnswerDTo;
import com.demo.demo.dtos.CompagnesDTo;
import com.demo.demo.dtos.Question_Tech_DTo;
import com.demo.demo.dtos.TestDTo;
import com.demo.demo.entity.*;
import com.demo.demo.mappers.TestMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final TestMapper testMapper;
    private final CompagnesRepository compagnesRepository;
    private Question_Logique_Repository questionLogiqueRepository;
    @Override
    public Test getTestById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    @Override
    public List<TestDTo> getAllTests() {
        return testRepository.findAll().stream()
                .map(testMapper::ToTestDto)
                .collect(Collectors.toList());
    }
    @Override
    public TestDTo createTest(TestDTo testDTo) {
        Long compagnesId = testDTo.getCompagnes_id();
        Compagnes compagnes = compagnesRepository.findById(compagnesId)
                .orElseThrow(() -> new EntityNotFoundException("Compagne not found with ID: " + compagnesId));

        Test test = testMapper.ToTest(testDTo);

       // test.setCompagnes(compagnes);

        Test savedTest = testRepository.save(test);

        // Associez le test enregistré à la compagnes
      //  compagnes.setTest(savedTest);
        compagnesRepository.save(compagnes);

        return testMapper.ToTestDto(savedTest);
    }

    @Override
    public TestDTo updateTest(Long id, TestDTo testDTo) {
        Optional<Test> existingTest = testRepository.findById(id);
        if (existingTest.isPresent()) {
            testMapper.updateTestFromDTO(testDTo, existingTest.get());
            return testMapper.ToTestDto(testRepository.save(existingTest.get()));
        }
        return null;
    }
    @Override
    public void deleteTest(Long id) {
            try {
                testRepository.deleteById(id);
                System.out.println("  test deleted successfully with ID: " + id);
            } catch (EmptyResultDataAccessException e) {
                System.out.println(" with ID " + id + " not found");
                throw e;
            } catch (Exception e) {
                System.err.println("Error deleting ");
                throw e;
            }
        }
    @Override
    public void deleteAllTests() {
        try {
            testRepository.deleteAll();
            System.out.println("All Tests deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all tests");
            throw e;
        }
    }
    public <T extends Question> int calculateTotalScore(List<T> questions) {
        int totalScore = 0;

        for (T question : questions) {
            if (question.getScore() != null) {
                totalScore += question.getScore();
            }
        }
        return totalScore;
    }
    public void CreateTest_Logique(Long CompagneId ) {
        Compagnes compagne = compagnesRepository.findById(CompagneId)
                .orElseThrow(() -> new RuntimeException("Compagne not found with id: " + CompagneId));
        Integer qtsNumber = compagne.getQtsNumber();
        Test test_logique =  new Test();
        if (qtsNumber != null && qtsNumber > 0) {
            List<Question_Logique> questionsLogiques = questionLogiqueRepository.findRandomQuestionsByDifficulty(compagne.getDifficulty(), qtsNumber);
            if (questionsLogiques.size() > qtsNumber) {
                throw new RuntimeException("Le nombre de questions générées dépasse qtsNumber");
            }
           test_logique.getQuestions().addAll(questionsLogiques);
            questionLogiqueRepository.saveAll(questionsLogiques);

       int totalScore = calculateTotalScore(test_logique.getQuestions());
       test_logique.setScoreTotale(totalScore);
            testRepository.save(test_logique);
        }


    }


}


