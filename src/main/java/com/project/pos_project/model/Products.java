package com.project.pos_project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// Entity class representing products in the system.
@Entity
public class Products {

    // Unique identifier for the product.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    // A unique identifier for a button associated with the product.
    private String buttonId;

    // The name of the product.
    private String productName;

    // The price of the product.
    private Double price;

    // The type or category of the product.
    private String type;

    // Indicates whether the product should be added to a button (for future upgrades of the app).
    private boolean addToButton;

    // The position of the product on the button if added to a button (for future upgrades of the app).
    private Integer buttonPosition;

    // One-to-Many relationship with BillProduct. A product can be present in multiple bills.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BillProduct> billProducts = new ArrayList<>();

    // Getters and setters for all fields.
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAddToButton() {
        return addToButton;
    }

    public void setAddToButton(boolean addToButton) {
        this.addToButton = addToButton;
    }

    public Integer getButtonPosition() {
        return buttonPosition;
    }

    public void setButtonPosition(Integer buttonPosition) {
        this.buttonPosition = buttonPosition;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }
}
