package org.neoedu.controller;

import org.neoedu.dto.ProgramDto;
import org.neoedu.dto.QuestionDto;
import org.neoedu.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<ProgramDto> createProgram(@RequestBody ProgramDto programDto) {
        return ResponseEntity.ok(programService.createProgram(programDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDto> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgramById(id));
    }

    @PostMapping("/{programId}/questions/{questionId}")
    public ResponseEntity<ProgramDto> addQuestionToProgram(
            @PathVariable Long programId,
            @PathVariable Long questionId) {
        return ResponseEntity.ok(programService.addQuestionToProgram(programId, questionId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<Page<QuestionDto>> getQuestionsInProgram(
            @PathVariable Long id,
            Pageable pageable) {
        return ResponseEntity.ok(programService.getQuestionsInProgram(id, pageable));
    }

    @GetMapping("/{id}/random-questions")
    public ResponseEntity<List<QuestionDto>> getRandomQuestionsFromProgram(
            @PathVariable Long id,
            @RequestParam int count) {
        return ResponseEntity.ok(programService.getRandomQuestionsFromProgram(id, count));
    }
}