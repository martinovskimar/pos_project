package com.project.pos_project.controller;

import com.project.pos_project.model.Products;
import com.project.pos_project.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Controller for handling login and home page requests.
@Controller
public class LoginController {

    @Autowired
    private ProductsService productsService;

    // Mapping for the login page.
    @GetMapping("/login")
    public String login() {
        // Return the login view.
        return "login";
    }

    // Mapping for the home page.
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        // Retrieve all products from the database.
        List<Products> products = productsService.getAllProducts();
        // Predefined button IDs for different rows on the home page.
        // Used for layout in the Thymeleaf template.
        List<String> buttonIdsRow1 = Arrays.asList("button2-1", "button2-2", "button2-3", "button2-4");
        List<String> buttonIdsRow2 = Arrays.asList("button3-1", "button3-2","button3-3", "button3-4");
        List<String> buttonIdsRow3 = Arrays.asList("button4-1", "button4-2", "button4-3", "button4-4");
        List<String> buttonIdsRow4 = Arrays.asList("button5-1", "button5-2", "button5-3", "button5-4");
        List<String> buttonIdsRow5 = Arrays.asList("button6-1", "button6-2", "button6-3", "button6-4");
        List<String> buttonIdsRow6 = Arrays.asList("button7-1", "button7-2", "button7-3", "button7-4");
        List<String> buttonIdsRow7 = Arrays.asList("button8-1", "button8-2", "button8-3", "button8-4");
        List<String> buttonIdsRow8 = Arrays.asList("button9-1", "button9-2", "button9-3", "button9-4");

        // Add all button ID lists to the model for rendering in the view.
        model.addAttribute("products", products);
        model.addAttribute("buttonIdsRow1", buttonIdsRow1);
        model.addAttribute("buttonIdsRow2", buttonIdsRow2);
        model.addAttribute("buttonIdsRow3", buttonIdsRow3);
        model.addAttribute("buttonIdsRow4", buttonIdsRow4);
        model.addAttribute("buttonIdsRow5", buttonIdsRow5);
        model.addAttribute("buttonIdsRow6", buttonIdsRow6);
        model.addAttribute("buttonIdsRow7", buttonIdsRow7);
        model.addAttribute("buttonIdsRow8", buttonIdsRow8);

        // Mapping of button IDs to product IDs, used for linking UI buttons to specific products.
        Map<String, Long> productMapping = new HashMap<>();
        for (Products product : products) {
            if (product.getButtonId() != null) {
                productMapping.put(product.getButtonId(), product.getProductId());
            }
        }
        model.addAttribute("productMapping", productMapping);

        // Populate whiskey button data
        List<String> whiskeyButtons = Arrays.asList(
                "whiskeyBtn-1-1", "whiskeyBtn-1-2", "whiskeyBtn-1-3", "whiskeyBtn-1-4",
                "whiskeyBtn-2-1", "whiskeyBtn-2-2", "whiskeyBtn-2-3", "whiskeyBtn-2-4",
                "whiskeyBtn-3-1", "whiskeyBtn-3-2", "whiskeyBtn-3-3", "whiskeyBtn-3-4",
                "whiskeyBtn-4-1", "whiskeyBtn-4-2", "whiskeyBtn-4-3", "whiskeyBtn-4-4"
        );
        model.addAttribute("whiskeyButtons", whiskeyButtons);

        // Populate spirits button data
        List<String> spiritsButtons = Arrays.asList(
                "spiritsBtn-1-1", "spiritsBtn-1-2", "spiritsBtn-1-3", "spiritsBtn-1-4",
                "spiritsBtn-2-1", "spiritsBtn-2-2", "spiritsBtn-2-3", "spiritsBtn-2-4",
                "spiritsBtn-3-1", "spiritsBtn-3-2", "spiritsBtn-3-3", "spiritsBtn-3-4",
                "spiritsBtn-4-1", "spiritsBtn-4-2", "spiritsBtn-4-3", "spiritsBtn-4-4"
        );
        model.addAttribute("spiritsButtons", spiritsButtons);

        // Populate minerals button data
        List<String> mineralsButtons = Arrays.asList(
                "mineralsBtn-1-1", "mineralsBtn-1-2", "mineralsBtn-1-3", "mineralsBtn-1-4",
                "mineralsBtn-2-1", "mineralsBtn-2-2", "mineralsBtn-2-3", "mineralsBtn-2-4",
                "mineralsBtn-3-1", "mineralsBtn-3-2", "mineralsBtn-3-3", "mineralsBtn-3-4",
                "mineralsBtn-4-1", "mineralsBtn-4-2", "mineralsBtn-4-3", "mineralsBtn-4-4"
        );
        model.addAttribute("mineralsButtons", mineralsButtons);

        // Populate tea/coffee button data
        List<String> teaCoffeeButtons = Arrays.asList(
                "teaCoffeeBtn-1-1", "teaCoffeeBtn-1-2", "teaCoffeeBtn-1-3", "teaCoffeeBtn-1-4",
                "teaCoffeeBtn-2-1", "teaCoffeeBtn-2-2", "teaCoffeeBtn-2-3", "teaCoffeeBtn-2-4",
                "teaCoffeeBtn-3-1", "teaCoffeeBtn-3-2", "teaCoffeeBtn-3-3", "teaCoffeeBtn-3-4",
                "teaCoffeeBtn-4-1", "teaCoffeeBtn-4-2", "teaCoffeeBtn-4-3", "teaCoffeeBtn-4-4"
        );
        model.addAttribute("teaCoffeeButtons", teaCoffeeButtons);

        // Populate food button data
        List<String> foodButtons  = Arrays.asList(
                "foodBtn-1-1", "foodBtn-1-2", "foodBtn-1-3", "foodBtn-1-4",
                "foodBtn-2-1", "foodBtn-2-2", "foodBtn-2-3", "foodBtn-2-4",
                "foodBtn-3-1", "foodBtn-3-2", "foodBtn-3-3", "foodBtn-3-4",
                "foodBtn-4-1", "foodBtn-4-2", "foodBtn-4-3", "foodBtn-4-4"
        );
        model.addAttribute("foodButtons", foodButtons);

        // Retrieve and add the logged-in username to the model.
        String loggedInUsername = principal.getName();
        model.addAttribute("loggedInUserName", loggedInUsername);

        // Add the current date and time to the model.
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String currentDate = now.format(dateFormatter);
        String currentTime = now.format(timeFormatter);

        model.addAttribute("currentDate", currentDate);
        model.addAttribute("currentTime", currentTime);

        // Return the home view.
        return "/home";
    }
}
