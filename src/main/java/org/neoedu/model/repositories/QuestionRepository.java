package org.neoedu.model.repositories;

import org.neoedu.model.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByThemeId(Long themeId);
    Page<Question> findByThemeId(Long themeId, Pageable pageable);
}
