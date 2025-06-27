package org.neoedu.service;

import org.neoedu.dto.QuestionDto;
import org.neoedu.exception.EntityNotFoundException;
import org.neoedu.model.Program;
import org.neoedu.model.Question;
import org.neoedu.model.Theme;
import org.neoedu.repository.QuestionRepository;
import org.neoedu.repository.ThemeRepository;
import org.neoedu.repository.ProgramRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ThemeRepository themeRepository;
    private final ProgramRepository programRepository;

    @Transactional
    public QuestionDto createQuestion(QuestionDto questionDto) {
        Theme theme = themeRepository.findById(questionDto.themeId())
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with id: " + questionDto.themeId()));

        Question question = new Question();
        question.setText(questionDto.text());
        question.setTheme(theme);

        Question savedQuestion = questionRepository.save(question);
        return convertToDto(savedQuestion);
    }

    public List<QuestionDto> getQuestionsByTheme(Long themeId) {
        return questionRepository.findByThemeId(themeId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public QuestionDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));
        return convertToDto(question);
    }

    @Transactional
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));

        // Remove question from all programs
        programRepository.findAllByQuestionsContaining(question)
                .forEach(program -> program.getQuestions().remove(question));

        questionRepository.delete(question);
    }

    private QuestionDto convertToDto(Question question) {
        Set<Long> programIds = question.getPrograms().stream()
                .map(Program::getId)
                .collect(Collectors.toSet());

        return new QuestionDto(
                question.getId(),
                question.getText(),
                question.getTheme().getId(),
                programIds
        );
    }
}