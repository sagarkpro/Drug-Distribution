package com.pharmacy.drug_distribution.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pharmacy.drug_distribution.entities.PharmacistDetail;

public interface PharmacistDetailsDao extends JpaRepository<PharmacistDetail, Integer> {
    
    public PharmacistDetail findByEmailAndPassword(String email, String password);

    @Modifying
    @Query(value = "update pharmacist_detail set full_name = :fullName, email = :email, password = :password, license = :license where id = :pharmacistId", nativeQuery = true)
    public int updateProfie(@Param("pharmacistId") Integer pharmacistId, @Param("email") String email, @Param("password") String password,@Param("fullName") String fullName, @Param("license") String license);
}
