package com.project.pos_project.controller;

import com.project.pos_project.dto.BillDto;
import com.project.pos_project.dto.BillProductDto;
import com.project.pos_project.model.Bill;
import com.project.pos_project.model.BillProduct;
import com.project.pos_project.model.Products;
import com.project.pos_project.repository.BillProductRepository;
import com.project.pos_project.repository.BillRepository;
import com.project.pos_project.repository.ProductsRepository;
import com.project.pos_project.repository.UserRepository;
import com.project.pos_project.service.BillService;
import com.project.pos_project.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller for handling bill-related operations.
@RestController
public class BillController {

    // Inject required repositories and services.
    @Autowired
    private BillProductRepository billProductRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private BillService billService;
    @Autowired
    private ProductsService productsService;

    // Endpoint for creating a new bill.
    @PostMapping("/api/bills")
    public ResponseEntity<?> createBill(@RequestBody BillDto billDto) {
        // Convert BillDto to Bill entity and processes it.
        Bill bill = billService.createBill(billDto);
        // Return the ID of the created bill
        return ResponseEntity.ok(bill.getBillId());
    }

    // Endpoint for retrieving a product ID by its name.
    @GetMapping("/api/products/id")
    public ResponseEntity<Long> getProductIdByName(@RequestParam String productName) {
        // Retrieve product by name, throws exception if not found.
        Products product = productsService.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        // Return the product ID.
        return ResponseEntity.ok(product.getProductId());
    }

    // Endpoint for getting all unpaid bills.
    @GetMapping("/api/bills/unpaid")
    public ResponseEntity<List<Bill>> getUnpaidBills() {
        // Retrieve list of unpaid bills.
        List<Bill> unpaidBills = billService.getUnpaidBills();
        // Return the list.
        return ResponseEntity.ok(unpaidBills);
    }

    // Endpoint for retrieving a bill by its ID.
    @GetMapping("/api/bills/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        // Find the bill by ID, throws exception if not found.
        Bill bill = billService.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        // Return the found bill.
        return ResponseEntity.ok(bill);
    }

    // Endpoint for updating an existing bill.
    @PutMapping("/api/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable Long billId, @RequestBody BillDto billDto) {
        // Find existing bill or throws exception if not found.
        Bill existingBill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        // Remove existing BillProducts from the database.
        System.out.println("Deleting existing bill products...");
        billProductRepository.deleteAll(existingBill.getBillProducts());
        System.out.println("Existing bill products deleted.");

        // Clear existing BillProducts from the Bill entity.
        existingBill.getBillProducts().clear();

        // Updates basic information of the bill (table number and payment method) from the DTO.
        existingBill.setTableNumber(billDto.getTableNumber());
        existingBill.setPayment(billDto.getPayment());

        // Clear existing BillProducts
        existingBill.getBillProducts().clear();

        // Initialize a variable to calculate total price.
        double totalPrice = 0;
        // Iterate over each product in the bill DTO, adding them to the bill entity.
        for (BillProductDto productDto : billDto.getBillProducts()) {
            // Retrieves each product by its ID, or throws an exception if not found.
            Products product = productsRepository.findById(productDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            BillProduct billProduct = new BillProduct();
            billProduct.setBill(existingBill);
            billProduct.setProduct(product);
            billProduct.setQuantity(productDto.getQuantity());
            // Calculate the total price.
            totalPrice += product.getPrice() * productDto.getQuantity();
            // Add the billProduct to the bill.
            existingBill.getBillProducts().add(billProduct);
        }

        // Set the calculated total price to the bill.
        existingBill.setTotalPrice(totalPrice);

        // Save the updated bill to the database.
        Bill updatedBill = billRepository.save(existingBill);
        // Return the updated bill.
        return ResponseEntity.ok(updatedBill);
    }

    // Endpoint for retrieving a closed bill by its ID.
    @GetMapping("/api/bills/closed/{billId}")
    public ResponseEntity<Bill> getClosedBillById(@PathVariable Long billId) {
        // Find the closed bill by its ID and return it, or return a 404 Not Found if it doesn't exist.
        return billService.findById(billId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint for reopening closed bill.
    @PutMapping("/api/bills/reopen/{billId}")
    public ResponseEntity<?> reopenBill(@PathVariable Long billId) {
        // Reopen the specified bill using the bill service.
        Bill reopenedBill = billService.reopenBill(billId);
        // Return the reopened bill.
        return ResponseEntity.ok(reopenedBill);
    }
}
