package org.neoedu.service;

import org.neoedu.dto.QuestionDto;
import org.neoedu.model.entities.Question;
//import org.springframework.data.domain.Page;
import java.util.List;

public interface QuestionService {
    Question createQuestion(QuestionDto questionDto);
    List<Question> findQuestionsByThemeId(Long themeId);
    Question findQuestionById(Long id);
    void deleteQuestion(Long id);
}
