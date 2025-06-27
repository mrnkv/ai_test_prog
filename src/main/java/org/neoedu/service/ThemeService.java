package org.neoedu.service;

import org.neoedu.dto.ThemeDto;
import org.neoedu.exception.EntityNotFoundException;
//import org.neoedu.dto.QuestionDto;
//import org.neoedu.model.Program;
import org.neoedu.model.Theme;
import org.neoedu.repository.ThemeRepository;
import org.neoedu.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public ThemeDto createTheme(ThemeDto themeDto) {
        Theme theme = new Theme();
        theme.setName(themeDto.name());
        theme.setDescription(themeDto.description());
        
        Theme savedTheme = themeRepository.save(theme);
        return convertToDto(savedTheme);
    }

    @Transactional
    public void deleteTheme(Long id) {
        // Каскадное удаление вопросов через orphanRemoval в Entity
        themeRepository.deleteById(id);
    }

    public ThemeDto getThemeById(Long id) {
        Theme theme = themeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with id: " + id));
        return convertToDto(theme);
    }

    public Page<ThemeDto> getQuestionsByTheme(Long themeId, Pageable pageable) {
        if (!themeRepository.existsById(themeId)) {
            throw new EntityNotFoundException("Theme not found with id: " + themeId);
        }
        
        return questionRepository.findByThemeId(themeId, pageable)
                .map(question -> new ThemeDto(
                    question.getTheme().getId(),
                    question.getTheme().getName(),
                    question.getTheme().getDescription()
                ));
    }

    public List<ThemeDto> getAllThemes() {
        return themeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ThemeDto updateTheme(Long id, ThemeDto themeDto) {
        Theme theme = themeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with id: " + id));
        
        theme.setName(themeDto.name());
        theme.setDescription(themeDto.description());
        
        Theme updatedTheme = themeRepository.save(theme);
        return convertToDto(updatedTheme);
    }

    private ThemeDto convertToDto(Theme theme) {
        return new ThemeDto(
                theme.getId(),
                theme.getName(),
                theme.getDescription()
        );
    }
}