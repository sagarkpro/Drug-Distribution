package com.pharmacy.drug_distribution.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pharmacy.drug_distribution.daos.DistributorDetailsDao;
import com.pharmacy.drug_distribution.daos.DistributorMedicineDao;
import com.pharmacy.drug_distribution.daos.MedicineDetailsDao;
import com.pharmacy.drug_distribution.daos.OrderDetailsDao;
import com.pharmacy.drug_distribution.daos.PharmacistDetailsDao;
import com.pharmacy.drug_distribution.dtos.BuyMedicineDto;
import com.pharmacy.drug_distribution.dtos.LoginDto;
import com.pharmacy.drug_distribution.dtos.LoginResponse;
import com.pharmacy.drug_distribution.dtos.RegisterDto;
import com.pharmacy.drug_distribution.dtos.ViewOrdersDto;
import com.pharmacy.drug_distribution.dtos.ViewProfileDto;
import com.pharmacy.drug_distribution.entities.DistributorMedicine;
import com.pharmacy.drug_distribution.entities.OrderDetails;
import com.pharmacy.drug_distribution.entities.PharmacistDetail;
import com.pharmacy.drug_distribution.entities.UserTypeEnum;

import jakarta.transaction.Transactional;


@Service
public class PharmacistServiceImpl implements PharmacistService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PharmacistDetailsDao pharmacistDetailsDao;

    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Autowired
    private DistributorDetailsDao distributorDetailsDao;

    @Autowired
    private MedicineDetailsDao medicineDetailsDao;

    @Autowired
    private DistributorMedicineDao distributorMedicineDao;

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        PharmacistDetail pharmacistDetail = pharmacistDetailsDao.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if(pharmacistDetail == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(modelMapper.map(pharmacistDetail, LoginResponse.class));
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        PharmacistDetail pharmacistDetail = modelMapper.map(registerDto, PharmacistDetail.class);
        pharmacistDetail.setUserType(UserTypeEnum.Pharmacist);
        pharmacistDetailsDao.save(pharmacistDetail);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> buyMedicine(BuyMedicineDto buyMedicine) {
        OrderDetails order = new OrderDetails();
        order.setDistributorId(distributorDetailsDao.findById(buyMedicine.getDistributorId()).orElseThrow());
        order.setMedicineId(medicineDetailsDao.findById(buyMedicine.getMedicineId()).orElseThrow());
        order.setQuantity(buyMedicine.getQuantity());
        order.setTotalAmount(buyMedicine.getQuantity()*(distributorMedicineDao.findByDistributorIdAndMedicineId(order.getDistributorId(),order.getMedicineId()).getPrice()));
        order.setPharmacistId(pharmacistDetailsDao.findById(buyMedicine.getPharmacistId()).orElseThrow());
        orderDetailsDao.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<?> viewProfile(Integer phamracistId) {
        PharmacistDetail pharmacistDetail = pharmacistDetailsDao.findById(phamracistId).orElseThrow();
        return ResponseEntity.ok().body(modelMapper.map(pharmacistDetail, ViewProfileDto.class));
    }

    @Override
    @Transactional
    public ResponseEntity<?> editProfile(Integer pharmacistId, RegisterDto dto) {
        pharmacistDetailsDao.updateProfie(pharmacistId, dto.getEmail(), dto.getPassword(), dto.getFullName(), dto.getLicense());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> viewOrders(Integer pharmacistId) {
        List<ViewOrdersDto> orderList = new ArrayList<>();
        for(OrderDetails order: orderDetailsDao.findByPharmacistId(pharmacistDetailsDao.findById(pharmacistId).orElseThrow())){
            ViewOrdersDto temp = modelMapper.map(order, ViewOrdersDto.class);
            temp.setDistributorName(order.getDistributorId().getFullName());
            temp.setMedicineName(order.getMedicineId().getName());
            temp.setPharmacistName(order.getPharmacistId().getFullName());
            orderList.add(temp);

        }
        return ResponseEntity.ok().body(orderList);
    }
    
}
