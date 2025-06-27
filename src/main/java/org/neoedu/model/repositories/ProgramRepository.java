package org.neoedu.model.repositories;

import org.neoedu.model.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    @Query("SELECT q FROM Program p JOIN p.questions q WHERE p.id = :programId")
    Page<Question> findQuestionsByProgramId(@Param("programId") Long programId, Pageable pageable);
    
    @Query(value = "SELECT q FROM Program p JOIN p.questions q WHERE p.id = :programId ORDER BY RANDOM()")
    List<Question> findRandomQuestionsByProgramId(@Param("programId") Long programId, Pageable pageable);
}
