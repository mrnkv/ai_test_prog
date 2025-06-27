package org.neoedu.repository;

import org.neoedu.model.Program;
import org.neoedu.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findAllByQuestionsContaining(Question question);
}