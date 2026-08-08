package com.newvatika.backend.controller;

import com.newvatika.backend.dto.DashboardResponse;
import com.newvatika.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {


    private final DashboardService dashboardService;


    @GetMapping("/stats")
    public ResponseEntity<DashboardResponse> getStats() {

        return ResponseEntity.ok(
                dashboardService.getStats()
        );
    }
}