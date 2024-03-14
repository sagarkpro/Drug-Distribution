package com.pharmacy.drug_distribution.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pharmacy.drug_distribution.daos.DistributorDetailsDao;
import com.pharmacy.drug_distribution.daos.DistributorMedicineDao;
import com.pharmacy.drug_distribution.daos.MedicineDetailsDao;
import com.pharmacy.drug_distribution.dtos.SellMedicineDto;
import com.pharmacy.drug_distribution.entities.DistributorMedicine;


@Service
public class DistributorMedicineServiceImpl implements DistributorMedicineService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private DistributorMedicineDao distributorMedicineDao;

    @Autowired
    private DistributorDetailsDao distributorDetailsDao;

    @Autowired
    private MedicineDetailsDao medicineDetailsDao;

    @Override
    public ResponseEntity<?> sellMedicine(SellMedicineDto sellMedicineDto) {
        DistributorMedicine distributorMedicine = modelMapper.map(sellMedicineDto, DistributorMedicine.class);
        distributorMedicine.setDistributorId(distributorDetailsDao.findById(sellMedicineDto.getDistributorId()).orElseThrow());
        distributorMedicine.setMedicineId(medicineDetailsDao.findById(sellMedicineDto.getMedicineId()).orElseThrow());
        distributorMedicineDao.save(distributorMedicine);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
