package org.neoedu.service.impl;

import org.neoedu.service.ProgramService;
import org.neoedu.dto.ProgramDto;
import org.neoedu.model.entities.Program;
import org.neoedu.model.entities.Question;
import org.neoedu.model.repositories.ProgramRepository;
import org.neoedu.model.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public Program createProgram(ProgramDto programDto) {
        Program program = new Program();
        program.setName(programDto.getName());
        program.setDescription(programDto.getDescription());
        
        if (programDto.getQuestionIds() != null) {
            Set<Question> questions = programDto.getQuestionIds().stream()
                .map(questionId -> questionRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Question not found with id: " + questionId)))
                .collect(Collectors.toSet());
            program.setQuestions(questions);
        }
        
        return programRepository.save(program);
    }

    @Override
    public Program getProgramById(Long id) {
        return programRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Program not found"));
    }

    @Override
    @Transactional
    public void addQuestionToProgram(Long programId, Long questionId) {
        Program program = programRepository.findById(programId)
            .orElseThrow(() -> new RuntimeException("Program not found"));
        
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new RuntimeException("Question not found"));
        
        program.getQuestions().add(question);
        programRepository.save(program);
    }

    @Override
    @Transactional
    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

    @Override
    public Page<Question> getQuestionsByProgramId(Long programId, Pageable pageable) {
        return programRepository.findQuestionsByProgramId(programId, pageable);
    }

    @Override
    public List<Question> getRandomQuestionsByProgramId(Long programId, int count) {
        Pageable pageable = PageRequest.of(0, count);
        return programRepository.findRandomQuestionsByProgramId(programId, pageable);
    }
}
