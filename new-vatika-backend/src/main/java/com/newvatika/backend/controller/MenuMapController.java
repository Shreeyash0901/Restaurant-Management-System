package com.newvatika.backend.controller;

import com.newvatika.backend.dto.MenuGroupResponse;
import com.newvatika.backend.service.MenuMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MenuMapController {

    private final MenuMapService menuMapService;

    @GetMapping
    public Map<String, MenuGroupResponse> getMenu() {
        return menuMapService.getGroupedMenu();
    }
}
