package com.project.pos_project.controller;

import com.project.pos_project.model.Products;
import com.project.pos_project.model.User;
import com.project.pos_project.service.ProductsService;
import com.project.pos_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// Define a controller for handling requests related to the admin functionalities.
@Controller
public class AdminController {

    // Autowired services to interact with products and users.
    @Autowired
    private ProductsService productsService;

    @Autowired
    private UserService userService;

    // Mapping for the admin page.
    @GetMapping("/admin")
    public String adminPage() {
        // Returns the admin view.
        return "admin";
    }

    // Mapping for adding a new product.
    @PostMapping("/admin/addproduct")
    public String addProduct(Products product, Model model) {
        // Check if a product with the same name already exists.
        if (productsService.existsByProductName(product.getProductName())) {
            // Add an error message to the model if the product exists.
            model.addAttribute("error", "Error: Product with name '" + product.getProductName() + "' already exists!");
            // Return to the admin page to display the error.
            return "admin"; // Stay on the admin page and display the error
        }
        // Save the new product if it does not exist.
        productsService.saveProduct(product);
        // Redirect back to the admin page after successful addition.
        return "redirect:/admin";
    }

    // Mapping for adding a new employee.
    @PostMapping("/admin/addemployee")
    public String addEmployee(User user) {
        // Adds the new user (employee) using the userService.
        userService.addUser(user);
        // Redirects back to the admin page after adding the employee.
        return "redirect:/admin";
    }

    // Mapping for accessing the user reports page.
    @GetMapping("/admin/user-reports")
    public String userReports() {
        // Returns the user reports view.
        return "user-reports";
    }
}
