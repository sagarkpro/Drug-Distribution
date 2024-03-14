package com.pharmacy.drug_distribution.services;

import org.springframework.http.ResponseEntity;

import com.pharmacy.drug_distribution.dtos.BuyMedicineDto;
import com.pharmacy.drug_distribution.dtos.LoginDto;
import com.pharmacy.drug_distribution.dtos.RegisterDto;

public interface PharmacistService {

    ResponseEntity<?> login(LoginDto loginDto);

    ResponseEntity<?> register(RegisterDto registerDto);

    ResponseEntity<?> buyMedicine(BuyMedicineDto buyMedicine);

    ResponseEntity<?> viewProfile(Integer phamracistId);

    ResponseEntity<?> editProfile(Integer pharmacistId, RegisterDto dto);

    ResponseEntity<?> viewOrders(Integer pharmacistId);
    
}
