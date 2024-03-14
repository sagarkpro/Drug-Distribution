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
public class BuyMedicineDto {
    private Integer pharmacistId;
    private Integer medicineId;
    private Integer distributorId;
    private int quantity;
}
