package com.newvatika.backend.controller;

import com.newvatika.backend.dto.ReportDashboardDto;
import com.newvatika.backend.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportsService reportsService;

    @GetMapping("/dashboard")
    public ReportDashboardDto getDashboardReport() {
        return reportsService.getDashboardReport();
    }
}