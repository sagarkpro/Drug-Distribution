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
public class ViewProfileDto {
    private String fullName;
    private String license;
    private String email;
    private String password;

}
