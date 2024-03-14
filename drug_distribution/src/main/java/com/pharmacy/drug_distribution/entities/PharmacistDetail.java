package com.pharmacy.drug_distribution.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class PharmacistDetail extends BaseEntity {
    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;

    private String fullName;
    private String license;

    @OneToMany(mappedBy = "pharmacistId")
    // @ToString.Exclude
    private List<OrderDetails> orderId = new ArrayList<>();

}
