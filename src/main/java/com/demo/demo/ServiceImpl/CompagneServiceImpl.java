package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.CompagnesRepository;
import com.demo.demo.Repository.QuestionRepository;
import com.demo.demo.Service.CompagneService;
import com.demo.demo.dtos.CompagnesDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Compagnes;
import com.demo.demo.entity.Question;
import com.demo.demo.mappers.CompagnesMappers;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompagneServiceImpl implements CompagneService {
    private  final CompagnesRepository compagnesRepository;
    private final CompagnesMappers compagnesMappers;
    private final QuestionRepository questionRepository;

    public CompagneServiceImpl(CompagnesRepository compagnesRepository, CompagnesMappers compagnesMappers, QuestionRepository questionRepository) {
        this.compagnesRepository = compagnesRepository;
        this.compagnesMappers = compagnesMappers;
        this.questionRepository = questionRepository;
    }

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

    public void affecterQuestionsACompagnes(Long compagneId, List<Long> questionIds) {
        Compagnes compagnes = compagnesRepository.findById(compagneId).orElse(null);

        if (compagnes != null) {
            List<Question> questions = questionRepository.findAllById(questionIds);
            if (questions.size() <= compagnes.getQtsNumber()) {
                compagnes.setQuestions(questions);
                int totalScore = 0;
                for (Question question : questions) {
                    if (question.getScore() != null) {
                        totalScore += question.getScore();
                    }
                }
                compagnes.setScore(totalScore);
                compagnesRepository.save(compagnes);
            } else {
                throw new IllegalArgumentException("Le nombre de questions dépasse qtsNumber");
            }
        } else {
            throw new IllegalArgumentException("Compagne avec l'ID " + compagneId + " non trouvée.");
        }

    }





}
