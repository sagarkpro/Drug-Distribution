package com.pharmacy.drug_distribution.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pharmacy.drug_distribution.daos.DistributorDetailsDao;
import com.pharmacy.drug_distribution.daos.OrderDetailsDao;
import com.pharmacy.drug_distribution.dtos.LoginDto;
import com.pharmacy.drug_distribution.dtos.LoginResponse;
import com.pharmacy.drug_distribution.dtos.RegisterDto;
import com.pharmacy.drug_distribution.dtos.ViewOrdersDto;
import com.pharmacy.drug_distribution.dtos.ViewProfileDto;
import com.pharmacy.drug_distribution.entities.DistributorDetail;
import com.pharmacy.drug_distribution.entities.OrderDetails;
import com.pharmacy.drug_distribution.entities.UserTypeEnum;

import jakarta.transaction.Transactional;


@Service
public class DistributorServieImpl implements DistributorService {

    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Autowired
    private DistributorDetailsDao distributorDetailsDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        DistributorDetail distributorDetail = distributorDetailsDao.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        // distributorDetail.getOrderId().size();
        return ResponseEntity.ok().body(modelMapper.map(distributorDetail, LoginResponse.class));
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        DistributorDetail distributorDetail = modelMapper.map(registerDto, DistributorDetail.class);
        distributorDetail.setUserType(UserTypeEnum.Distributor);
        distributorDetailsDao.save(distributorDetail);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> viewProfile(Integer distributorId) {
        return ResponseEntity.ok().body(modelMapper.map(distributorDetailsDao.findById(distributorId).orElseThrow(), ViewProfileDto.class));
    }

    @Override
    @Transactional
    public ResponseEntity<?> editProfile(Integer pharmacistId, RegisterDto dto) {
        distributorDetailsDao.updateProfie(pharmacistId, dto.getEmail(), dto.getPassword(), dto.getFullName());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> viewOrders(Integer distributorId) {
        List<ViewOrdersDto> orderList = new ArrayList<>();
        for(OrderDetails order: orderDetailsDao.findByDistributorId(distributorDetailsDao.findById(distributorId).orElseThrow())){
            ViewOrdersDto temp = modelMapper.map(order, ViewOrdersDto.class);
            temp.setDistributorName(order.getDistributorId().getFullName());
            temp.setMedicineName(order.getMedicineId().getName());
            temp.setPharmacistName(order.getPharmacistId().getFullName());
            orderList.add(temp);

        }
        return ResponseEntity.ok().body(orderList);
    }

}
