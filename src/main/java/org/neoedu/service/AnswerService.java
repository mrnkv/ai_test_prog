package org.neoedu.service;

import org.neoedu.dto.AnswerDto;
import org.neoedu.exception.EntityNotFoundException;
import org.neoedu.model.Answer;
import org.neoedu.model.Question;
import org.neoedu.repository.AnswerRepository;
import org.neoedu.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public AnswerDto createAnswer(Long questionId, AnswerDto answerDto) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

        Answer answer = new Answer();
        answer.setText(answerDto.text());
        answer.setRight(answerDto.isRight());
        answer.setInfo(answerDto.info());
        answer.setQuestion(question);

        Answer savedAnswer = answerRepository.save(answer);
        return convertToDto(savedAnswer);
    }

    public List<AnswerDto> getAnswersByQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    private AnswerDto convertToDto(Answer answer) {
        return new AnswerDto(
                answer.getId(),
                answer.getText(),
                answer.isRight(),
                answer.getInfo(),
                answer.getQuestion().getId()
        );
    }
}