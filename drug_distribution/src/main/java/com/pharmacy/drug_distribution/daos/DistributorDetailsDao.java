package com.pharmacy.drug_distribution.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pharmacy.drug_distribution.entities.DistributorDetail;

public interface DistributorDetailsDao extends JpaRepository<DistributorDetail, Integer>{

    DistributorDetail findByEmailAndPassword(String email, String password);

    @Modifying
    @Query(value = "update distributor_detail set full_name = :fullName, email = :email, password = :password where id = :pharmacistId", nativeQuery = true)
    int updateProfie(Integer pharmacistId, String email, String password, String fullName);
    
}
