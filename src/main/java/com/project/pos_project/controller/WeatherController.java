package com.project.pos_project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// Controller for handling weather requests.
@RestController
public class WeatherController {

    // Inject the API key for the weather service from application properties.
    @Value("${weather.api.key}")
    private String apiKey;

    // Endpoint to get weather information for a specific city.
    @GetMapping("/api/weather")
    public String getWeather(@RequestParam String city,
                             @RequestParam(required = false, defaultValue = "metric") String units) {
        // Construct the URL for the weather API request, including the city, units, and API key.
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=" + units + "&appid=" + apiKey;
        // RestTemplate used to make the HTTP request to the weather API.
        RestTemplate restTemplate = new RestTemplate();
        // Return the response from the weather API as a String.
        return restTemplate.getForObject(url, String.class);
    }
}
