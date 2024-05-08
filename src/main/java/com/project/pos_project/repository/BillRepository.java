package com.project.pos_project.repository;

import com.project.pos_project.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

// Repository interface for Bill entities.
public interface BillRepository extends JpaRepository<Bill, Long> {

    // Custom method to find bills with a null value for the payment field.
    List<Bill> findByPaymentIsNull();

    // Custom method to find bills with a non-null value for the payment field.
    List<Bill> findByPaymentIsNotNull();

    // Custom method to find bills by a user's username and date.
    List<Bill> findByUserUsernameAndDate(String username, LocalDate date);

}
