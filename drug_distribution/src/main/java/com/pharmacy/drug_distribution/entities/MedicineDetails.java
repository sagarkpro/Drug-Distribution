package com.pharmacy.drug_distribution.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class MedicineDetails extends BaseEntity{
    private String name;

    @Enumerated(EnumType.STRING)
    private MedicineTypeEnum type;

    @ManyToMany
    private List<DistributorDetail> distributorId = new ArrayList<>();

    @OneToMany(mappedBy = "medicineId")
    private List<DistributorMedicine> distributorMedicineId;
}
