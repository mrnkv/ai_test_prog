package org.neoedu.service;

import org.neoedu.dto.ProgramDto;
import org.neoedu.dto.QuestionDto;
import org.neoedu.exception.EntityNotFoundException;
import org.neoedu.model.Program;
import org.neoedu.model.Question;
import org.neoedu.repository.ProgramRepository;
import org.neoedu.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public ProgramDto createProgram(ProgramDto programDto) {
        Program program = new Program();
        program.setName(programDto.name());
        program.setDescription(programDto.description());

        Program savedProgram = programRepository.save(program);
        return convertToDto(savedProgram);
    }

    public ProgramDto getProgramById(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + id));
        return convertToDto(program);
    }

    @Transactional
    public ProgramDto addQuestionToProgram(Long programId, Long questionId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + programId));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

        program.getQuestions().add(question);
        Program updatedProgram = programRepository.save(program);
        return convertToDto(updatedProgram);
    }

    @Transactional
    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

    public Page<QuestionDto> getQuestionsInProgram(Long programId, Pageable pageable) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + programId));

        return questionRepository.findByProgramsContaining(program, pageable)
                .map(this::convertToQuestionDto);
    }

    public List<QuestionDto> getRandomQuestionsFromProgram(Long programId, int count) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + programId));

        List<Question> questions = program.getQuestions().stream().toList();
        Collections.shuffle(questions);

        return questions.stream()
                .limit(count)
                .map(this::convertToQuestionDto)
                .collect(Collectors.toList());
    }

    private ProgramDto convertToDto(Program program) {
        Set<Long> questionIds = program.getQuestions().stream()
                .map(Question::getId)
                .collect(Collectors.toSet());

        return new ProgramDto(
                program.getId(),
                program.getName(),
                program.getDescription(),
                questionIds
        );
    }

    private QuestionDto convertToQuestionDto(Question question) {
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