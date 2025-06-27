package org.neoedu.service.impl;

import org.neoedu.service.AnswerService;
import org.neoedu.dto.AnswerDto;
import org.neoedu.model.entities.Answer;
import org.neoedu.model.entities.Question;
import org.neoedu.model.repositories.AnswerRepository;
import org.neoedu.model.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public Answer createAnswer(AnswerDto answerDto) {
        Question question = questionRepository.findById(answerDto.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question not found"));
        
        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setIsRight(answerDto.getIsRight());
        answer.setInfo(answerDto.getInfo());
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> findAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    @Transactional
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}
