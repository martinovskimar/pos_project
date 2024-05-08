package com.project.pos_project.controller;

import com.project.pos_project.service.CalorieNinjasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controller for handling calorie information requests.
@RestController
public class CalorieController {

    // Service for accessing calorie information.
    private final CalorieNinjasService calorieNinjasService;

    // Constructor to inject the CalorieNinjasService.
    public CalorieController(CalorieNinjasService calorieNinjasService) {
        this.calorieNinjasService = calorieNinjasService;
    }

    // Endpoint to get calorie information for a specific food item.
    @GetMapping("/api/calories")
    public ResponseEntity<String> getCalorieInfo(@RequestParam String foodItem) {
        try {
            // Use the calorieNinjasService to fetch calorie information.
            String calorieInfo = calorieNinjasService.getCaloriesInfo(foodItem);
            // Return the fetched calorie information.
            return ResponseEntity.ok(calorieInfo);
        } catch (Exception e) {
            // Handle exceptions and return a 500 error response.
            return ResponseEntity.status(500).body("Error fetching calorie information");
        }
    }
}
