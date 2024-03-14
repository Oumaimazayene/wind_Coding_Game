package com.demo.demo.Repository;

import com.demo.demo.entity.Question_Logique;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Question_Logique_Repository  extends JpaRepository<Question_Logique,Long> {

    @Query("SELECT ql FROM Question_Logique ql ORDER BY FUNCTION('RANDOM') LIMIT :qtsNumber")
    List<Question_Logique> findRandomQuestions(@Param("qtsNumber") int qtsNumber);
    @Query(value = "SELECT * FROM questions_logiques WHERE difficulty = :difficulty ORDER BY RAND() LIMIT :qtsNumber", nativeQuery = true)
    List<Question_Logique> findRandomQuestionsByDifficulty(String difficulty, int qtsNumber);

}




