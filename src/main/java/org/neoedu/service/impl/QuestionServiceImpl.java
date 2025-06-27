package org.neoedu.service.impl;

import org.neoedu.service.QuestionService;
import org.neoedu.dto.QuestionDto;
import org.neoedu.model.entities.Question;
import org.neoedu.model.entities.Theme;
import org.neoedu.model.repositories.QuestionRepository;
import org.neoedu.model.repositories.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ThemeRepository themeRepository;

    @Override
    @Transactional
    public Question createQuestion(QuestionDto questionDto) {
        Theme theme = themeRepository.findById(questionDto.getThemeId())
            .orElseThrow(() -> new RuntimeException("Theme not found"));
        
        Question question = new Question();
        question.setText(questionDto.getText());
        question.setTheme(theme);
        return questionRepository.save(question);
    }

    @Override
    public List<Question> findQuestionsByThemeId(Long themeId) {
        return questionRepository.findByThemeId(themeId);
    }

    @Override
    public Question findQuestionById(Long id) {
        return questionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    @Transactional
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not found"));
        questionRepository.delete(question);
    }
}
