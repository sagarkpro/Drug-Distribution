package com.pharmacy.drug_distribution.dtos;

import com.pharmacy.drug_distribution.entities.UserTypeEnum;

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
public class RegisterDto {
    private String fullName;
    private String email;
    private String password;
    private String license;
    private UserTypeEnum userType;
}
