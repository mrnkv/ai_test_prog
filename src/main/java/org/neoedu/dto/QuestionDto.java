package org.neoedu.dto;

import java.util.Set;

public record QuestionDto(Long id, String text, Long themeId, Set<Long> programIds) {}