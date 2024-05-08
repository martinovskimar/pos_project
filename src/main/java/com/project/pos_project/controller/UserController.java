package com.project.pos_project.controller;

import com.project.pos_project.model.User;
import com.project.pos_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Controller for handling employee operations in the admin panel.
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to display the list of all employees.
    @GetMapping("/admin/employees")
    public String showEmployeeList(Model model) {
        // Retrieves all employees from the database.
        List<User> employees = userService.getAllUsers();
        // Add the list of employees to the model for rendering in the view.
        model.addAttribute("employees", employees);
        // Return the view for displaying the employee list.
        return "employeeList";
    }

    // Endpoint to show the form for updating an employee's details.
    @GetMapping("/admin/updateemployee/{userId}")
    public String showUpdateEmployeeForm(@PathVariable Long userId, Model model) {
        // Retrieve an employee by their ID or throws an exception if not found.
        User employee = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + userId));
        // Add the employee details to the model for the update form.
        model.addAttribute("employee", employee);
        // Return the view for the update employee form.
        return "updateEmployee";
    }

    // Endpoint for submitting the employees updated details.
    @PostMapping("/admin/updateemployee/{userId}")
    public String updateEmployee(@PathVariable Long userId, User employeeDetails) {
        // Update the employee details in the database.
        userService.updateUser(userId, employeeDetails);
        // Redirect to the employee list view after successful update.
        return "redirect:/admin/employees";
    }

    // Endpoint for deleting an employee.
    @GetMapping("/deleteemployee/{userId}")
    public String deleteEmployee(@PathVariable Long userId) {
        // Delete the employee with the specified ID from the database.
        userService.deleteUser(userId);
        // Redirect to the employee list view after successful deletion.
        return "redirect:/admin/employees";
    }

}
