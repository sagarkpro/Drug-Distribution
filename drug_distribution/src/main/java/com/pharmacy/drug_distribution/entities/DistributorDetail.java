package com.pharmacy.drug_distribution.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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
public class DistributorDetail extends BaseEntity {
    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;
    
    private String fullName;
    
    @ManyToMany(mappedBy = "distributorId")
    private List<MedicineDetails> medicineId = new ArrayList<>();

    @OneToMany(mappedBy = "distributorId")
    private List<OrderDetails> orderId = new ArrayList<>();

    @OneToMany(mappedBy = "distributorId")
    private List<DistributorMedicine> distributorMedicineId;
}
