package org.neoedu.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private String text;
    private Boolean isRight;
    private String info;
    private Long questionId;
}
