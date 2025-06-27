package org.neoedu.controller;

import org.neoedu.dto.AnswerDto;
import org.neoedu.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/for-question/{questionId}")
    public ResponseEntity<AnswerDto> createAnswer(
            @PathVariable Long questionId,
            @RequestBody AnswerDto answerDto) {
        return ResponseEntity.ok(answerService.createAnswer(questionId, answerDto));
    }

    @GetMapping("/for-question/{questionId}")
    public ResponseEntity<List<AnswerDto>> getAnswersByQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(answerService.getAnswersByQuestion(questionId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}