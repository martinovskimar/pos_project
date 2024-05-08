package com.project.pos_project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// Entity class representing a bill in the system.
@Entity
public class Bill {

    // Unique identifier for the bill
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billId;

    // Date when the bill was created
    private LocalDate date;

    // Time when the bill was created
    private LocalTime time;

    // Many-to-One relationship with User. Each bill is associated with one user.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // User who created the bill

    // The table number associated with the bill
    private int tableNumber;

    // Total price of all products in the bill
    private double totalPrice;

    // One-to-Many relationship with BillProduct. A bill can have multiple products.
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    // Prevents recursive serialization issues in JSON output
    @JsonManagedReference
    private List<BillProduct> billProducts = new ArrayList<>();

    // Payment method or status of the bill
    private String payment;

    // Getters and setters for all fields
    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BillProduct> getBillProducts() {
        return billProducts;
    }

    public void setBillProducts(List<BillProduct> billProducts) {
        this.billProducts = billProducts;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
