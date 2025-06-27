package org.neoedu.repository;

import org.neoedu.model.Program;
import org.neoedu.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByThemeId(Long themeId);
    Page<Question> findByThemeId(Long themeId, Pageable pageable);
    Page<Question> findByProgramsContaining(Program program, Pageable pageable);
}