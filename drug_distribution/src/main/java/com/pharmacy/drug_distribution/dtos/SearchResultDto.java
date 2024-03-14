package com.pharmacy.drug_distribution.dtos;

import java.util.List;

import com.pharmacy.drug_distribution.entities.MedicineTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchResultDto {
    private String id;
    private String name;
    private MedicineTypeEnum type;
    private List<String> distributorNames;
    private List<Integer> distributorId;

}
