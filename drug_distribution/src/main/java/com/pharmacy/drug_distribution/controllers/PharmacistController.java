package com.pharmacy.drug_distribution.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.drug_distribution.dtos.BuyMedicineDto;
import com.pharmacy.drug_distribution.dtos.RegisterDto;
import com.pharmacy.drug_distribution.services.MedicineService;
import com.pharmacy.drug_distribution.services.PharmacistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/pharmacist")
@CrossOrigin
public class PharmacistController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PharmacistService pharmacistService;
    
    @PostMapping("/buyMedicine")
    public ResponseEntity<?> buyMedicine(@RequestBody BuyMedicineDto buyMedicine) {
        return pharmacistService.buyMedicine(buyMedicine);
    }

    @GetMapping("/viewProfile/{pharmacistId}")
    public ResponseEntity<?> viewProfile(@PathVariable Integer pharmacistId) {
        return pharmacistService.viewProfile(pharmacistId);
    }

    @PutMapping("/editProfile/{pharmacistId}")
    public ResponseEntity<?> editProfile(@PathVariable Integer pharmacistId, @RequestBody RegisterDto dto) {
        return pharmacistService.editProfile(pharmacistId, dto);
    }


    @GetMapping("/viewOrders/{pharmacistId}")
    public ResponseEntity<?> viewOrders(@PathVariable Integer pharmacistId) {
        return pharmacistService.viewOrders(pharmacistId);
    }
    
    
}
