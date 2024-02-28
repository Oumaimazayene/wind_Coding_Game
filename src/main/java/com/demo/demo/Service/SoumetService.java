package com.demo.demo.Service;

import com.demo.demo.dtos.AnswerDTo;
import com.demo.demo.dtos.SoumetDTo;
import com.demo.demo.entity.Answer;
import com.demo.demo.entity.Soumet;

import java.util.List;

public interface SoumetService {

    Soumet getsoumetTestById(Long id);
    List<SoumetDTo> getAll();
    SoumetDTo create(SoumetDTo soumetDTo);
    SoumetDTo update(Long id,SoumetDTo soumetDTo);
    void delete(Long id);
    void  deleteAll();
}
