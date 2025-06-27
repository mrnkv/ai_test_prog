package org.neoedu.dto;

import lombok.Data;
import java.util.Set;

@Data
public class ProgramDto {
    private String name;
    private String description;
    private Set<Long> questionIds;
}
