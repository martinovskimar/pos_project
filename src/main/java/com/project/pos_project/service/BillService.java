package com.project.pos_project.service;

import com.project.pos_project.dto.BillDto;
import com.project.pos_project.dto.BillProductDto;
import com.project.pos_project.model.Bill;
import com.project.pos_project.model.BillProduct;
import com.project.pos_project.model.Products;
import com.project.pos_project.model.User;
import com.project.pos_project.repository.BillRepository;
import com.project.pos_project.repository.ProductsRepository;
import com.project.pos_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

// Service class for handling operations related to bills.
@Service
public class BillService {

    private final BillRepository billRepository;
    private final ProductsRepository productsRepository;

    @Autowired
    private UserRepository userRepository;

    // Constructor injection for repositories.
    @Autowired
    public BillService(BillRepository billRepository, ProductsRepository productsRepository) {
        this.billRepository = billRepository;
        this.productsRepository = productsRepository;
    }

    // Create a new bill or updates an existing one based on BillDto.
    @Transactional
    public Bill createBill(BillDto billDto) {
        Bill bill;
        if (billDto.getBillId() != 0) {
            // If billId is present, update existing bill.
            bill = billRepository.findById(billDto.getBillId())
                    .orElseThrow(() -> new RuntimeException("Bill not found"));

        } else {
            // Create a new bill.
            bill = new Bill();
            bill.setDate(LocalDate.now());
            bill.setTime(LocalTime.now());
            bill.setTableNumber(billDto.getTableNumber());

            // Fetch user based on username and set to bill.
            User user = userRepository.findByUsername(billDto.getUserName())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            bill.setUser(user);

            // Calculate total price and build bill products.
            double totalPrice = 0;
            for (BillProductDto productDto : billDto.getBillProducts()) {
                Products product = productsRepository.findById(productDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                BillProduct billProduct = new BillProduct();
                billProduct.setProduct(product);
                billProduct.setQuantity(productDto.getQuantity());
                billProduct.setBill(bill);
                bill.getBillProducts().add(billProduct);

                totalPrice += product.getPrice() * productDto.getQuantity();
            }

            bill.setTotalPrice(totalPrice);
        }

        // Set payment for the bill and save it.
        bill.setPayment(billDto.getPayment());
        return billRepository.save(bill);
    }

    // Retrieves a list of unpaid bills.
    public List<Bill> getUnpaidBills() {
        return billRepository.findByPaymentIsNull();
    }

    // Find a bill by its ID.
    public Optional<Bill> findById(Long billId) {
        return billRepository.findById(billId);
    }

    // Save a bill to the database.
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    // Retrieve a list of closed (paid) bills.
    public List<Bill> getClosedBills() {
        return billRepository.findByPaymentIsNotNull();
    }

    // Reopen a closed bill by setting its payment to null.
    @Transactional
    public Bill reopenBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        // Set payment to null to indicate that the bill is reopened.
        bill.setPayment(null);

        // Save the updated bill back to the repository.
        return billRepository.save(bill);
    }
}
