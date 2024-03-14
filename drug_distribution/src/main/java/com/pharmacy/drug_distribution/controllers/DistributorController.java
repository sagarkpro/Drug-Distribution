package com.pharmacy.drug_distribution.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.drug_distribution.dtos.RegisterDto;
import com.pharmacy.drug_distribution.dtos.SellMedicineDto;
import com.pharmacy.drug_distribution.services.DistributorMedicineService;
import com.pharmacy.drug_distribution.services.DistributorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/distributor")
@CrossOrigin
public class DistributorController {

    @Autowired
    private DistributorService distributorService;
    
    @Autowired
    private DistributorMedicineService distributorMedicineService;

    @PostMapping("/sellMedicine")
    public ResponseEntity<?> sellMedicine(@RequestBody SellMedicineDto sellMedicineDto) {
        return distributorMedicineService.sellMedicine(sellMedicineDto);
    }

    @GetMapping("/viewProfile/{distributorId}")
    public ResponseEntity<?> viewProfile(@PathVariable Integer distributorId) {
        return distributorService.viewProfile(distributorId);
    }
    
    @PutMapping("/editProfile/{distributorId}")
    public ResponseEntity<?> editProfile(@PathVariable Integer distributorId, @RequestBody RegisterDto dto) {
        return distributorService.editProfile(distributorId, dto);
    }

    @GetMapping("/viewOrders/{distributorId}")
    public ResponseEntity<?> viewOrders(@PathVariable Integer distributorId) {
        return distributorService.viewOrders(distributorId);
    }
    
    
}
