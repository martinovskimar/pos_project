package com.project.pos_project.service;

import com.project.pos_project.model.Products;
import com.project.pos_project.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service class for handling operations related to products.
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    // Constructor for dependency injection of the ProductsRepository.
    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    // Retrieve all products from the repository.
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    // Adds a new product to the repository.
    public Products addProduct(Products product) {
        return productsRepository.save(product);
    }

    // Save or update a product in the repository.
    public void saveProduct(Products product) {
        productsRepository.save(product);
    }

    // Find a product by its ID.
    public Optional<Products> getProductById(Long id) {
        return productsRepository.findById(id);
    }

    // Find a product by its name.
    public Optional<Products> findByName(String productName) {
        return productsRepository.findByProductName(productName);
    }

    // Update a product's details based on the product name.
    public Products updateProductByName(String productName, Products productDetails) {
        Products product = productsRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found with name: " + productName));
        product.setPrice(productDetails.getPrice());
        // Update other fields as necessary
        return productsRepository.save(product);
    }

    // Delete a product by its ID.
    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }

    // Updates a product's details based on the product ID.
    public Products updateProduct(Long productId, Products productDetails) {
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Update the product details
        product.setProductName(productDetails.getProductName());
        product.setPrice(productDetails.getPrice());
        product.setAddToButton(productDetails.isAddToButton());
        product.setButtonPosition(productDetails.getButtonPosition());

        return productsRepository.save(product);
    }

    // Update the button details of a product. (for future updates of the app).
    public Products updateButtonDetails(Long productId, boolean addToButton, Integer buttonPosition) {
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        product.setAddToButton(addToButton);
        product.setButtonPosition(buttonPosition);

        return productsRepository.save(product);
    }

    // Assign a button position to a product. (for future updates of the app).
    public void assignButtonPosition(Long productId, Integer buttonPosition) {
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        product.setButtonPosition(buttonPosition);
        productsRepository.save(product);
    }

    // Find products by their type.
    public List<Products> findByType(String type) {
        return productsRepository.findByType(type);
    }

    // Delete a product by its ID.
    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

    // Find product by its button ID.
    public Optional<Products> findByButtonId(String buttonId) {
        return productsRepository.findByButtonId(buttonId);
    }

    // Checks if a product with a given name already exists in the repository.
    public boolean existsByProductName(String productName) {
        return productsRepository.existsByProductName(productName);
    }
}
