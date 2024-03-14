package com.pharmacy.drug_distribution.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.drug_distribution.entities.MedicineDetails;

public interface MedicineDetailsDao  extends JpaRepository<MedicineDetails, Integer>{

    List<MedicineDetails> findByNameContaining(String name);
    
}
