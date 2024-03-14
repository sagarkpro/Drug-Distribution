package com.pharmacy.drug_distribution.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViewMedicineDto {
    private String medicineName;
    private String distributorName;
    private Integer medicineId;
    private Integer distributorId;
    private double price;
}
