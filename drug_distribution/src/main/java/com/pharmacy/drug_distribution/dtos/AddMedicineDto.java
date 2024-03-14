package com.pharmacy.drug_distribution.dtos;

import com.pharmacy.drug_distribution.entities.MedicineTypeEnum;

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
public class AddMedicineDto {
    private String name;
    private MedicineTypeEnum type;
    private double price;
    private int stock;
}
