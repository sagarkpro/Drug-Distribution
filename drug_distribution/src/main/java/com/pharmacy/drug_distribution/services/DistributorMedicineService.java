package com.pharmacy.drug_distribution.services;

import org.springframework.http.ResponseEntity;

import com.pharmacy.drug_distribution.dtos.SellMedicineDto;

public interface DistributorMedicineService{

    ResponseEntity<?> sellMedicine(SellMedicineDto sellMedicineDto);
    
}
