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
@ToString(callSuper = true)
public class OrderDetails extends BaseEntity{

    @ManyToOne
    private PharmacistDetail pharmacistId;

    @ManyToOne
    private MedicineDetails medicineId;

    @ManyToOne
    private DistributorDetail distributorId;
    private int quantity;
    private double totalAmount;
}
