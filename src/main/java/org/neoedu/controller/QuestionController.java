package org.neoedu.controller;

import org.neoedu.service.QuestionService;
import org.neoedu.dto.QuestionDto;
import org.neoedu.model.entities.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Question createQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.createQuestion(questionDto);
    }

    @GetMapping("/theme/{themeId}")
    public List<Question> findQuestionsByThemeId(@PathVariable Long themeId) {
        return questionService.findQuestionsByThemeId(themeId);
    }

    @GetMapping("/{id}")
    public Question findQuestionById(@PathVariable Long id) {
        return questionService.findQuestionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
