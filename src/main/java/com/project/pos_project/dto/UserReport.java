package com.project.pos_project.dto;

import java.time.LocalDate;
import java.util.Map;

// DTO class for representing a user report.
public class UserReport {

    // Username of the user to whom the report belongs
    private String username;
    // Date of the report
    private LocalDate date;
    // Map of payment types and their corresponding amounts
    private Map<String, Double> payments;
    // Total payment amount in the report
    private Double totalPayment;
    // Map of product names and their sold quantities
    private Map<String, Integer> productQuantities;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Double> getPayments() {
        return payments;
    }

    public void setPayments(Map<String, Double> payments) {
        this.payments = payments;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Map<String, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<String, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }
}
