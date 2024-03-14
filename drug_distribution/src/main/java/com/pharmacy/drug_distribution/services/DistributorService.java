package com.pharmacy.drug_distribution.services;

import org.springframework.http.ResponseEntity;

import com.pharmacy.drug_distribution.dtos.LoginDto;
import com.pharmacy.drug_distribution.dtos.RegisterDto;

public interface DistributorService {

    ResponseEntity<?> login(LoginDto loginDto);

    ResponseEntity<?> register(RegisterDto registerDto);

    ResponseEntity<?> viewProfile(Integer distributorId);

    ResponseEntity<?> editProfile(Integer pharmacistId, RegisterDto dto);

    ResponseEntity<?> viewOrders(Integer distributorId);
    
}
