package com.project.pos_project.dto;

// DTO class for representing a product in a bill.
public class BillProductDto {

    // ID of the product
    private long productId;
    // Quantity of the product in the bill
    private int quantity;

    // Getters and setters for each field
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
