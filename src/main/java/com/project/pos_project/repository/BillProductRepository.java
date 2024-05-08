package com.project.pos_project.repository;

import com.project.pos_project.model.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for BillProduct entities.(no custom methods).
public interface BillProductRepository extends JpaRepository<BillProduct, Long> {
}
