package com.demo.demo.Controller;

import com.demo.demo.Service.Question_Logique_Service;
import com.demo.demo.dtos.CandidateDTO;
import com.demo.demo.dtos.Question_Logique_DTo;
import com.demo.demo.dtos.Question_Tech_DTo;
import com.demo.demo.dtos.SoumetDTo;
import com.demo.demo.entity.Candidate;
import com.demo.demo.entity.Question_Logique;
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
public class Question_LogiqueController {
    private final Question_Logique_Service questionLogiqueService;

    public Question_LogiqueController(Question_Logique_Service questionLogiqueService) {
        this.questionLogiqueService = questionLogiqueService;
    }

    @GetMapping("/{id}")
    public Question_Logique getQuestionLogiqueById(@PathVariable Long id) {
        return questionLogiqueService.getQuestionLogiqueById(id);
    }


    @GetMapping("/all")
    public List<Question_Logique_DTo> getAllQuestionLogique() {
        return questionLogiqueService.getAllQuestionLogique();
    }

    @PostMapping(value = "/add", consumes={"multipart/form-data"})
    public ResponseEntity<?> createQuestionLogique(
            @RequestPart("questionLogiqueDto") Question_Logique_DTo questionLogiqueDto,
            @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            Question_Logique_DTo createdQuestionLogique = questionLogiqueService.createQuestionLogique(questionLogiqueDto, imageFile);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionLogique);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
