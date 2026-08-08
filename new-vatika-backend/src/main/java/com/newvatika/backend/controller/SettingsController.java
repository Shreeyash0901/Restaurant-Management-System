package com.newvatika.backend.controller;

import com.newvatika.backend.dto.PasswordChangeRequest;
import com.newvatika.backend.dto.SettingsDto;
import com.newvatika.backend.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping
    public SettingsDto getSettings() {
        return settingsService.getSettings();
    }

    @PutMapping
    public SettingsDto updateSettings(
            @RequestBody SettingsDto dto) {

        return settingsService.updateSettings(dto);
    }

    @PutMapping("/change-password")
    public String changePassword(
            Authentication authentication,
            @RequestBody PasswordChangeRequest request) {

        return settingsService.changePassword(
                authentication.getName(),
                request
        );
    }

}