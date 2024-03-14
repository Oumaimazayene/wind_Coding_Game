package com.demo.demo.ServiceImpl;


import com.demo.demo.Repository.DomaineRepository;
import com.demo.demo.Repository.Question_Tech_Repository;
import com.demo.demo.Repository.TypeRepository;
import com.demo.demo.Service.Question_Tech_Service;
import com.demo.demo.dtos.Question_Tech_DTo;
import com.demo.demo.entity.*;
import com.demo.demo.mappers.AnswerMapper;
import com.demo.demo.mappers.Question_Tech_Mapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class Question_Tech_ServiceImpl implements Question_Tech_Service {

    private  final Question_Tech_Repository questionTechRepository;
    private final Question_Tech_Mapper questionTechMapper;
    private  final DomaineRepository domaineRepository;
    private  final TypeRepository typeRepository ;
    private final AnswerMapper answerMapper ;


    @Override
    public Question_Tech getQuestion_techById(Long id) {
        return  questionTechRepository.findById(id).orElse(null);
    }



    @Override
    public List<Question_Tech_DTo> getAllQuestionTech() {
        return questionTechRepository.findAll().stream()
                .map(questionTechMapper::ToQuestionTechDTo)
                .collect(Collectors.toList());    }

@Override
    public Question_Tech_DTo createQuestionTech(Question_Tech_DTo questionTechDTo) {
        Long domainId = questionTechDTo.getDomain_id();
        Long typeId = questionTechDTo.getType_id();

        Domaine domaine = domaineRepository.findById(domainId)
                .orElseThrow(() -> new RuntimeException("Domaine non trouvé avec l'id " + domainId));

        Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Type non trouvé avec l'id " + typeId));

        Question_Tech questionTech = questionTechMapper.ToQuestionTech(questionTechDTo);
        questionTech.setDomain(domaine);
        questionTech.setType(type);
    List<Answer> answerList = questionTechDTo.getAnswer().stream()
            .map(answerDto -> {
                Answer answer = answerMapper.ToAnswer(answerDto);

                answer.setQuestion(questionTech);

                return answer;
            })
            .collect(Collectors.toList());

    questionTech.setAnswer(answerList);


    Question_Tech savedQuestionTech = questionTechRepository.save(questionTech);

    Question_Tech_DTo responseDto = questionTechMapper.ToQuestionTechDTo(savedQuestionTech);

        return responseDto;
    }


    @Override
    public Question_Tech_DTo updateQuestionTech(Long id, Question_Tech_DTo questionTechDTo) {
            Optional<Question_Tech> existingQuestionTech = questionTechRepository.findById(id);
            if (existingQuestionTech.isPresent()) {
                questionTechMapper.updateQuestionTechFromDTO(questionTechDTo, existingQuestionTech.get());
                return questionTechMapper.ToQuestionTechDTo(questionTechRepository.save(existingQuestionTech.get()));
            }
            return null;
        }

    @Override
    public void deleteQuestionTech(Long id) {
        try {
            questionTechRepository.deleteById(id);
            System.out.println("question Technique deleted successfully with ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("question Technique with ID " + id + " not found");
            throw e;
        } catch (Exception e) {
            System.err.println("Error deleting question Technique");
            throw e;
        }
    }

    @Override
    public void deleteAllQuestionTech() {
        try {
            questionTechRepository.deleteAll();
            System.out.println("All questions Technique deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting all questions Techniques");
            throw e;
        }
    }


}
