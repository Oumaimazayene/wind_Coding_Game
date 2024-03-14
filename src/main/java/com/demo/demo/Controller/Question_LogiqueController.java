package com.demo.demo.Controller;

import com.demo.demo.Repository.QuestionRepository;
import com.demo.demo.Repository.Question_Logique_Repository;
import com.demo.demo.Service.Question_Logique_Service;
import com.demo.demo.dtos.CandidateDTO;
import com.demo.demo.dtos.Question_Logique_DTo;
import com.demo.demo.dtos.Question_Tech_DTo;
import com.demo.demo.dtos.SoumetDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Question_Logique;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questionsLogique")
@AllArgsConstructor
public class Question_LogiqueController {
    private final Question_Logique_Service questionLogiqueService;
    private  final Question_Logique_Repository questionLogiqueRepository;
    private final QuestionRepository questionRepository;


    @GetMapping("/{id}")
    public Question_Logique getQuestionLogiqueById(@PathVariable Long id) {
        return questionLogiqueService.getQuestionLogiqueById(id);
    }


    @GetMapping("/all")
    public List<Question_Logique_DTo> getAllQuestionLogique() {
        return questionLogiqueService.getAllQuestionLogique();
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<?> createQuestionLogique(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam String questionLogiqueDtoJson
    ) {
        try {
            System.out.println("Received JSON: " + questionLogiqueDtoJson);
            System.out.println(imageFile);

            ObjectMapper objectMapper = new ObjectMapper();
            Question_Logique_DTo questionLogiqueDto = objectMapper.readValue(questionLogiqueDtoJson, Question_Logique_DTo.class);
            System.out.println(questionLogiqueDto);


            Question_Logique_DTo createdQuestionLogique = questionLogiqueService.createQuestionLogique(questionLogiqueDto, imageFile);

            return new ResponseEntity<>(createdQuestionLogique, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Erreur lors du traitement JSON: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>("Erreur d'entrée/sortie lors du traitement du fichier image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/{id}")

    public Question_Logique_DTo updateQuestionLogique(@PathVariable Long id, @RequestBody Question_Logique_DTo questionLogiqueDTo) {
        return questionLogiqueService.updateQuestionLogique(id,questionLogiqueDTo);
    }

    @DeleteMapping(value = "/{id}"
    )
    public void deleteQuestionLogique(@PathVariable Long id) {
        questionLogiqueService.deleteQuestionLogique(id);
    }

    @DeleteMapping("/questionsLogiques")
    public void deleteAllQuestionLogique() {
        questionLogiqueService.deleteAllQuestionLogique();
    }

}
