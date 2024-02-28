package com.demo.demo.ServiceImpl;

import com.demo.demo.Repository.Question_Logique_Repository;
import com.demo.demo.Repository.TypeRepository;
import com.demo.demo.Service.Question_Logique_Service;
import com.demo.demo.dtos.Question_Logique_DTo;
import com.demo.demo.entity.Answer;
import com.demo.demo.entity.Question_Logique;
import com.demo.demo.entity.Type;
import com.demo.demo.mappers.AnswerMapper;
import com.demo.demo.mappers.Question_Logique_Mapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class Question_Logique_ServiceImpl implements Question_Logique_Service {
    private final Question_Logique_Mapper questionLogiqueMapper;
    private final Question_Logique_Repository questionLogiqueRepository;
    private final TypeRepository typeRepository;
    private final AnswerMapper answerMapper ;

    public Question_Logique_ServiceImpl(Question_Logique_Mapper questionLogiqueMapper, Question_Logique_Repository questionLogiqueRepository, TypeRepository typeRepository, AnswerMapper answerMapper ) {
        this.questionLogiqueMapper = questionLogiqueMapper;
        this.questionLogiqueRepository = questionLogiqueRepository;
        this.typeRepository = typeRepository;
        this.answerMapper = answerMapper;
    }

    @Override
    public Question_Logique getQuestionLogiqueById(Long id) {
        return questionLogiqueRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question_Logique_DTo> getAllQuestionLogique() {
        return questionLogiqueRepository.findAll().stream()
                .map(questionLogiqueMapper::ToQuestionLogiqueDTo)
                .collect(Collectors.toList());
    }

    @Override
    public Question_Logique_DTo createQuestionLogique(Question_Logique_DTo questionLogiqueDto, MultipartFile imageFile) throws IOException {
        try {
            Long typeId = questionLogiqueDto.getType_id();
            Type type = typeRepository.findById(typeId)
                    .orElseThrow(() -> new RuntimeException("Type non trouvé avec l'id " + typeId));
            String imageUrl = saveImage(imageFile);


            Question_Logique questionLogique = questionLogiqueMapper.ToQuestionLogique(questionLogiqueDto);
            questionLogique.setType(type);

            List<Answer> answerList = questionLogiqueDto.getAnswer().stream()
                    .map(answerDto -> {
                        Answer answer = answerMapper.ToAnswer(answerDto);
                        answer.setQuestion(questionLogique);
                        return answer;
                    })
                    .collect(Collectors.toList());

            questionLogique.setAnswer(answerList);
            questionLogique.setURLimage(imageUrl);

            Question_Logique savedQuestionLogique = questionLogiqueRepository.save(questionLogique);

            return questionLogiqueMapper.ToQuestionLogiqueDTo(savedQuestionLogique);
        } catch (Exception e) {
            throw new RuntimeException("Error creating Question_Logique: " + e.getMessage(), e);
        }
    }



    private String saveImage(MultipartFile imageFile) throws IOException {
        Path uploadDir = Paths.get("./uploads");

        Files.createDirectories(uploadDir);

        String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        Path filePath = uploadDir.resolve(fileName);
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return  fileName;
    }



    @Override
public Question_Logique_DTo updateQuestionLogique(Long id, Question_Logique_DTo questionLogiqueDTo) {
    Optional<Question_Logique> existingQuestionLogique = questionLogiqueRepository.findById(id);
    if (existingQuestionLogique.isPresent()) {
        questionLogiqueMapper.updateQuestionLogiqueFromDTO(questionLogiqueDTo, existingQuestionLogique.get());
        return questionLogiqueMapper.ToQuestionLogiqueDTo(questionLogiqueRepository.save(existingQuestionLogique.get()));
    }
    return null;
}

@Override
public void deleteQuestionLogique(Long id) {
    try {
        questionLogiqueRepository.deleteById(id);
        System.out.println("question Logique deleted successfully with ID: " + id);
    } catch (EmptyResultDataAccessException e) {
        System.out.println("question logique with ID " + id + " not found");
        throw e;
    } catch (Exception e) {
        System.err.println("Error deleting question logique");
        throw e;
    }
}

@Override
public void deleteAllQuestionLogique() {
    try {
        questionLogiqueRepository.deleteAll();
        System.out.println("All question_logique deleted successfully");
    } catch (Exception e) {
        System.err.println("Error deleting all questions_logiques");
        throw e;
    }
}
}
