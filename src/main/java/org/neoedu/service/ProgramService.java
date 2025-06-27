package org.neoedu.service;

import org.neoedu.dto.ProgramDto;
import org.neoedu.model.entities.Program;
import org.neoedu.model.entities.Question;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ProgramService {
    Program createProgram(ProgramDto programDto);
    Program getProgramById(Long id);
    void addQuestionToProgram(Long programId, Long questionId);
    void deleteProgram(Long id);
    Page<Question> getQuestionsByProgramId(Long programId, Pageable pageable);
    List<Question> getRandomQuestionsByProgramId(Long programId, int count);
}
