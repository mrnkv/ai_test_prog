package org.neoedu.controller;

import org.neoedu.service.ThemeService;
import org.neoedu.dto.ThemeDto;
import org.neoedu.model.entities.Theme;
import org.neoedu.model.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping
    public Theme createTheme(@RequestBody ThemeDto themeDto) {
        return themeService.createTheme(themeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTheme(@PathVariable Long id) {
        themeService.deleteTheme(id);
    }

    @GetMapping("/{id}")
    public Theme getThemeById(@PathVariable Long id) {
        return themeService.getThemeById(id);
    }

    @GetMapping("/{id}/questions")
    public Page<Question> getQuestionsByThemeId(
            @PathVariable Long id,
            Pageable pageable) {
        return themeService.getQuestionsByThemeId(id, pageable);
    }
}
