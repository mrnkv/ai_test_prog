package org.neoedu.service;

import org.neoedu.dto.AnswerDto;
import org.neoedu.model.entities.Answer;
import java.util.List;

public interface AnswerService {
    Answer createAnswer(AnswerDto answerDto);
    List<Answer> findAnswersByQuestionId(Long questionId);
    void deleteAnswer(Long id);
}
