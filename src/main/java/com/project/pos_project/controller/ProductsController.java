package com.project.pos_project.controller;

import com.project.pos_project.model.Products;
import com.project.pos_project.service.ProductsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

// Controller for handling products operations.
@Controller
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    // Endpoint to get all products.
    @GetMapping
    public List<Products> getAllProducts() {
        // Return a list of all products.
        return productsService.getAllProducts();
    }

    // Endpoint to get a product by its ID.
    @GetMapping("/product{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        // Find a product by ID or throw exception if not found.
        Products product = productsService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        // Return the found product.
        return ResponseEntity.ok(product);
    }

    // Endpoint to delete a product.
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpServletRequest request) {
        // Find the product by ID.
        Products product = productsService.getProductById(id).orElse(null);
        if (product != null) {
            // Delete the product if found.
            productsService.deleteProductById(id);
            // Redirect back to the referring page.
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }
        // Redirect to admin page if the product is not found.
        return "redirect:/admin"; // Fallback redirect
    }

    // Endpoint to update product details.
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, Products productDetails) {
        // Update the product details.
        Products updatedProduct = productsService.updateProduct(id, productDetails);

        // Redirect based on the type of the product.
        if ("Food".equals(updatedProduct.getType())) {
            return "redirect:/admin/products/food";
        } else if ("Drinks".equals(updatedProduct.getType())) {
            return "redirect:/admin/products/drinks";
        } else {
            // Fallback redirect if the product type is not Food or Drinks.
            return "redirect:/admin";
        }
    }

    // Endpoint to show a list of food products.
    @GetMapping("/admin/products/food")
    public String showFoodList(Model model) {
        // Retrieve a list of food products.
        List<Products> foodList = productsService.findByType("Food");
        // Add the list to the model.
        model.addAttribute("products", foodList);
        // Return the view for displaying food products.
        return "foodList";
    }

    // Endpoint to show a list of drink products.
    @GetMapping("/admin/products/drinks")
    public String showDrinksList(Model model) {
        // Retrieve a list of drink products.
        List<Products> drinksList = productsService.findByType("Drinks");
        // Add the list to the model.
        model.addAttribute("products", drinksList);
        // Return the view for displaying drink products.
        return "drinksList";
    }

    // Endpoint to display the form for updating a product.
    @GetMapping("/admin/updateproduct/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        // Retrieve the product by its ID or throw exception if it's not found.
        Products product = productsService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        // Add the product to the model (used to populate the update form fields).
        model.addAttribute("product", product);
        // Return the view for the update product form.
        return "updateProduct";
    }

    // Endpoint for assigning a button to a specific product.
    @PostMapping("/admin/assignButtonToProduct")
    public String assignButtonToProduct(@RequestParam("productId") Long productId,
                                        @RequestParam("buttonId") String buttonId,
                                        RedirectAttributes redirectAttributes) {
        // Check if the button ID is already in use by a different product.
        Optional<Products> existingProduct = productsService.findByButtonId(buttonId);

        if (existingProduct.isPresent() && existingProduct.get().getProductId() != productId) {
            // If the button is already in use, add an error message and redirect back.
            redirectAttributes.addFlashAttribute("error", "Button ID " + buttonId + " is already in use.");
            return "redirect:/admin/assignbutton/" + productId;
        }

        // Retrieve the product by its ID or throws an exception if not found.
        Products product = productsService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));

        // Set the button ID for the product and saves the changes.
        product.setButtonId(buttonId);
        productsService.saveProduct(product);
        // Add a success message and redirects to the admin page.
        redirectAttributes.addFlashAttribute("success", "Button assigned successfully.");
        return "redirect:/admin";
    }

    // Endpoint to show the form for assigning a button to a product.
    @GetMapping("/admin/assignbutton/{productId}")
    public String showAssignButtonForm(@PathVariable Long productId, Model model) {
        // Retrieve the product by its ID or throws an exception if it's not found.
        Products product = productsService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
        // Add the product ID to the model (used in the form to assign a button).
        model.addAttribute("productId", product.getProductId());
        // Return the view for the assign button to product form.
        return "assignButtonToProduct";
    }
}
