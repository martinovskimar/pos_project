package com.project.pos_project.repository;

import com.project.pos_project.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository interface for Products entities.
@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    // Custom method to find a product by its name.
    Optional<Products> findByProductName(String productName);

    // Custom method to find products by their type.
    List<Products> findByType(String type);

    // Custom method to find a product by its buttonId.
    Optional<Products> findByButtonId(String buttonId);

    // Two methods for future upgrades of the app.
    List<Products> findByAddToButton(boolean addToButton);
    Optional<Products> findByButtonPosition(Integer buttonPosition);

    // Custom method to check if a product with a given name exists.
    boolean existsByProductName(String productName);
}
