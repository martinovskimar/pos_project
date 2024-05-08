package com.project.pos_project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Service class for interacting with the CalorieNinjas API.
@Service
public class CalorieNinjasService {

    // The API key for the CalorieNinjas API, injected from application properties.
    @Value("${calorieninjas.api.key}")
    private String apiKey;

    // RestTemplate for making HTTP requests.
    private final RestTemplate restTemplate;

    public CalorieNinjasService() {
        this.restTemplate = new RestTemplate();

        // Configure the RestTemplate to add the API key to each request.
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("X-Api-Key", apiKey);
            return execution.execute(request, body);
        });
    }

    // Fetch calorie and nutrition information for a given food item.
    public String getCaloriesInfo(String foodItem) {
        String url = "https://api.calorieninjas.com/v1/nutrition?query=" + foodItem;
        return restTemplate.getForObject(url, String.class);

    }
}
