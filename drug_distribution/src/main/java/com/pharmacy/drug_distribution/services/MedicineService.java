package com.pharmacy.drug_distribution.services;

import org.springframework.http.ResponseEntity;

import com.pharmacy.drug_distribution.dtos.AddMedicineDto;
import com.pharmacy.drug_distribution.dtos.BuyMedicineDto;

public interface MedicineService {

    ResponseEntity<?> addMedicine(AddMedicineDto dto);

    ResponseEntity<?> searchMedicine(String medicineName);

    ResponseEntity<?> viewMedicine(Integer medicineId);
    
}
