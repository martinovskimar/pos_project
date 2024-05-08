package com.project.pos_project.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

// DTO class for representing a bill, used for transferring data between layers.
public class BillDto {

    private String userName;
    private long billId;
    private LocalDate date;
    private LocalTime time;
    private long userId;
    private int tableNumber;
    // List of products associated with the bill
    private List<BillProductDto> billProducts;

    private String payment;


    // Getters and setters for each field
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<BillProductDto> getBillProducts() {
        return billProducts;
    }

    public void setBillProducts(List<BillProductDto> billProducts) {
        this.billProducts = billProducts;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
