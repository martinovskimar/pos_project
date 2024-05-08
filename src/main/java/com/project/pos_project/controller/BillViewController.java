package com.project.pos_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controller responsible for handling views related to bill functions.
@Controller
public class BillViewController {

    // Mapping for the bill functions page.
    @GetMapping("/bill-functions")
    public String billFunctions() {
        // Return the name of the Thymeleaf template to be rendered for the bill functions view.
        return "bill-functions";
    }
}
