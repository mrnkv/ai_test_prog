package org.neoedu.service;

import org.neoedu.dto.ThemeDto;
import org.neoedu.model.entities.Theme;
import org.neoedu.model.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThemeService {
    Theme createTheme(ThemeDto themeDto);
    void deleteTheme(Long id);
    Theme getThemeById(Long id);
    Page<Question> getQuestionsByThemeId(Long themeId, Pageable pageable);
}
