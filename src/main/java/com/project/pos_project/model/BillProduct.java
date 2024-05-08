package com.project.pos_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

// Entity class representing a product within a bill.
@Entity
public class BillProduct {

    // Unique identifier for the BillProduct
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with Bill. Each BillProduct is associated with one bill.
    @ManyToOne
    @JoinColumn(name = "bill_id")
    // Prevents recursive serialization issues in JSON output
    @JsonBackReference
    private Bill bill;

    // Many-to-One relationship with Products. Each BillProduct is associated with one product.
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    // Quantity of the product in the bill
    private int quantity;

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
