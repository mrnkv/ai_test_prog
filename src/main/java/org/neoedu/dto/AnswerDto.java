package org.neoedu.dto;

public record AnswerDto(Long id, String text, boolean isRight, String info, Long questionId) {}