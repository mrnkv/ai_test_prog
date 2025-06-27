package org.neoedu.service.impl;

import org.neoedu.service.ThemeService;
import org.neoedu.dto.ThemeDto;
import org.neoedu.model.entities.Theme;
import org.neoedu.model.entities.Question;
import org.neoedu.model.repositories.ThemeRepository;
import org.neoedu.model.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public Theme createTheme(ThemeDto themeDto) {
        Theme theme = new Theme();
        theme.setName(themeDto.getName());
        theme.setDescription(themeDto.getDescription());
        return themeRepository.save(theme);
    }

    @Override
    @Transactional
    public void deleteTheme(Long id) {
        Theme theme = themeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Theme not found"));
        themeRepository.delete(theme);
    }

    @Override
    public Theme getThemeById(Long id) {
        return themeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Theme not found"));
    }

    @Override
    public Page<Question> getQuestionsByThemeId(Long themeId, Pageable pageable) {
        return questionRepository.findByThemeId(themeId, pageable);
    }
}
