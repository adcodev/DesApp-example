package com.appecommerce.ecommerce.infrastructure.controller;

import com.appecommerce.ecommerce.core.usecase.dto.response.UserReportResponse;
import com.appecommerce.ecommerce.core.usecase.port.in.UserReportCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserReportsController {

    private final UserReportCase userReportCase;

    public UserReportsController(UserReportCase userReportCase) {
        this.userReportCase = userReportCase;
    }

    // --- NUEVO: Endpoint de reportes con filtros y paginación ---
    @GetMapping("/reports")
    public ResponseEntity<UserReportResponse> getReport(
            @RequestParam(required = false) String emailType,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        UserReportResponse report = userReportCase.getReport(emailType, role, page, size);
        return ResponseEntity.ok(report);
    }
}