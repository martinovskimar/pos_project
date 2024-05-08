package com.project.pos_project.service;

import com.project.pos_project.dto.UserReport;
import com.project.pos_project.model.Bill;
import com.project.pos_project.model.BillProduct;
import com.project.pos_project.repository.BillRepository;
import com.project.pos_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Service class for generating reports.
@Service
public class ReportService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    // Method to generate a report for a user on a specific date.
    public UserReport generateUserReport(String username, LocalDate date) {
        // Retrieve bills for the specified user and date.
        List<Bill> bills = billRepository.findByUserUsernameAndDate(username, date);
        // Maps to store payment methods and product quantities.
        Map<String, Double> payments = new HashMap<>();
        Map<String, Integer> productQuantities = new HashMap<>();

        double totalPayment = 0;
        for (Bill bill : bills) {
            // Accumulate payments by method.
            String paymentMethod = bill.getPayment();
            if (paymentMethod != null) {
                double billTotal = bill.getTotalPrice();
                BigDecimal roundedTotal = BigDecimal.valueOf(billTotal).setScale(2, RoundingMode.HALF_UP);
                payments.put(paymentMethod, payments.getOrDefault(paymentMethod, 0.0) + roundedTotal.doubleValue());
                totalPayment += billTotal;
            }

            // Accumulate product quantities.
            for (BillProduct billProduct : bill.getBillProducts()) {
                String productName = billProduct.getProduct().getProductName();
                int quantity = billProduct.getQuantity();
                productQuantities.put(productName, productQuantities.getOrDefault(productName, 0) + quantity);
            }
        }

        // Create the user report with calculated data.
        UserReport report = new UserReport();
        report.setUsername(username);
        report.setDate(date);
        report.setPayments(payments);
        BigDecimal roundedTotalPayment = BigDecimal.valueOf(totalPayment).setScale(2, RoundingMode.HALF_UP);
        report.setTotalPayment(roundedTotalPayment.doubleValue());
        report.setProductQuantities(productQuantities);

        // Returning the generated report.
        return report;
    }
}
