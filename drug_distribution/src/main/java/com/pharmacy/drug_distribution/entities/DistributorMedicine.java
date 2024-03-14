package com.pharmacy.drug_distribution.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DistributorMedicine extends BaseEntity {

    @ManyToOne
    private DistributorDetail distributorId;

    @ManyToOne
    private MedicineDetails medicineId;

    private double price;
}
