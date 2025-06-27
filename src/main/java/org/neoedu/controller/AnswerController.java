package org.neoedu.controller;

import org.neoedu.service.AnswerService;
import org.neoedu.dto.AnswerDto;
import org.neoedu.model.entities.Answer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public Answer createAnswer(@RequestBody AnswerDto answerDto) {
        return answerService.createAnswer(answerDto);
    }

    @GetMapping("/question/{questionId}")
    public List<Answer> findAnswersByQuestionId(@PathVariable Long questionId) {
        return answerService.findAnswersByQuestionId(questionId);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }
}
