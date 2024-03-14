package com.demo.demo.Repository;

import com.demo.demo.entity.Question_Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Question_Tech_Repository extends JpaRepository<Question_Tech,Long> {
    @Query(value = "SELECT * FROM Question_Technique WHERE domaine = :domaine ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Question_Tech> findRandomByDomaine(@Param("domaine") String domaine, @Param("limit") int limit);

    @Query(value = "SELECT * FROM Question_Technique WHERE domaine = :domaine1 ORDER BY RAND() LIMIT :limit1 " +
            "UNION " +
            "SELECT * FROM Question_Technique WHERE domaine = :domaine2 ORDER BY RAND() LIMIT :limit2 " +
            "UNION " +
            "SELECT * FROM Question_Technique WHERE domaine = :domaine3 ORDER BY RAND() LIMIT :limit3", nativeQuery = true)
    List<Question_Tech> findRandomByMultipleDomaines(
            @Param("domaine1") String domaine1, @Param("limit1") int limit1,
            @Param("domaine2") String domaine2, @Param("limit2") int limit2,
            @Param("domaine3") String domaine3, @Param("limit3") int limit3);
}
