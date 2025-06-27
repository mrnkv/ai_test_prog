package org.neoedu.controller;

import org.neoedu.service.ProgramService;
import org.neoedu.dto.ProgramDto;
import org.neoedu.model.entities.Program;
import org.neoedu.model.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping
    public Program createProgram(@RequestBody ProgramDto programDto) {
        return programService.createProgram(programDto);
    }

    @GetMapping("/{id}")
    public Program getProgramById(@PathVariable Long id) {
        return programService.getProgramById(id);
    }

    @PostMapping("/{programId}/questions/{questionId}")
    public void addQuestionToProgram(
            @PathVariable Long programId,
            @PathVariable Long questionId) {
        programService.addQuestionToProgram(programId, questionId);
    }

    @DeleteMapping("/{id}")
    public void deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
    }

    @GetMapping("/{id}/questions")
    public Page<Question> getQuestionsByProgramId(
            @PathVariable Long id,
            Pageable pageable) {
        return programService.getQuestionsByProgramId(id, pageable);
    }

    @GetMapping("/{id}/questions/random")
    public List<Question> getRandomQuestionsByProgramId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "10") int count) {
        return programService.getRandomQuestionsByProgramId(id, count);
    }
}
