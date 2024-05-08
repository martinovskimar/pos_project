package com.project.pos_project.controller;

import com.project.pos_project.dto.UserReport;
import com.project.pos_project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

// Controller for handling report requests.
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Endpoint for processing user report requests.
    @PostMapping("/admin/user-reports")
    public String handleUserReport(
            @RequestParam String username,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        try {
            // Generate a user report based on the provided username and date.
            UserReport report = reportService.generateUserReport(username, date);
            // Add the generated report to the model for rendering in the view.
            model.addAttribute("userReport", report);
            // Return the name of the Thymeleaf template to render the report results.
            return "user-report-results";
        } catch (Exception e) {
            //  Add an error message to the model in case of any error during report generation.
            model.addAttribute("error", "Error generating report");
            // Redirect back to the user reports form page with an error message.
            return "user-reports";
        }
    }
}
