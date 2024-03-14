package com.pharmacy.drug_distribution.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.drug_distribution.entities.DistributorDetail;
import com.pharmacy.drug_distribution.entities.OrderDetails;
import com.pharmacy.drug_distribution.entities.PharmacistDetail;

public interface OrderDetailsDao extends JpaRepository <OrderDetails, Integer>{

    List<OrderDetails> findByDistributorId(DistributorDetail distributorId);
    List<OrderDetails> findByPharmacistId(PharmacistDetail pharmacistId);
    
}
