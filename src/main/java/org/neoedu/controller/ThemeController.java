package org.neoedu.controller;

import org.neoedu.dto.QuestionDto;
import org.neoedu.dto.ThemeDto;
import org.neoedu.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/themes")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    @PostMapping
    public ResponseEntity<ThemeDto> createTheme(@RequestBody ThemeDto themeDto) {
        return ResponseEntity.ok(themeService.createTheme(themeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheme(@PathVariable Long id) {
        themeService.deleteTheme(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> getThemeById(@PathVariable Long id) {
        return ResponseEntity.ok(themeService.getThemeById(id));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<Page<ThemeDto>> getQuestionsByTheme(
            @PathVariable Long id,
            Pageable pageable) {
        return ResponseEntity.ok(themeService.getQuestionsByTheme(id, pageable));
    }
}