package com.demo.demo.Service;

import com.demo.demo.dtos.Question_Logique_DTo;
import com.demo.demo.dtos.RoleDTo;
import com.demo.demo.entity.Question_Logique;
import com.demo.demo.entity.Role;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface Question_Logique_Service {
    Question_Logique getQuestionLogiqueById(Long id);
    List<Question_Logique_DTo> getAllQuestionLogique();

    Question_Logique_DTo createQuestionLogique(Question_Logique_DTo questionLogiqueDto,MultipartFile imageFile)throws IOException;
    Question_Logique_DTo updateQuestionLogique(Long id, Question_Logique_DTo questionLogiqueDTo);
    void deleteQuestionLogique(Long id);
    void  deleteAllQuestionLogique();
}
