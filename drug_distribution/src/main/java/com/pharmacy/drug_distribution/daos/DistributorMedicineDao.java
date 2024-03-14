package com.pharmacy.drug_distribution.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.drug_distribution.entities.DistributorDetail;
import com.pharmacy.drug_distribution.entities.DistributorMedicine;
import com.pharmacy.drug_distribution.entities.MedicineDetails;

public interface DistributorMedicineDao  extends JpaRepository<DistributorMedicine, Integer> {
    public DistributorMedicine findByDistributorIdAndMedicineId(DistributorDetail dist, MedicineDetails med);

    public List<DistributorMedicine> findByMedicineId(MedicineDetails medicineId);
}