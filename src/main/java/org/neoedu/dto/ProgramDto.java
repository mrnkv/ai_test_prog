package org.neoedu.dto;

import java.util.Set;

public record ProgramDto(Long id, String name, String description, Set<Long> questionIds) {}