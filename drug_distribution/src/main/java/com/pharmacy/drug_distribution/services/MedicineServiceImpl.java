package com.pharmacy.drug_distribution.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pharmacy.drug_distribution.daos.DistributorMedicineDao;
import com.pharmacy.drug_distribution.daos.MedicineDetailsDao;
import com.pharmacy.drug_distribution.dtos.AddMedicineDto;
import com.pharmacy.drug_distribution.dtos.BuyMedicineDto;
import com.pharmacy.drug_distribution.dtos.SearchResultDto;
import com.pharmacy.drug_distribution.dtos.ViewMedicineDto;
import com.pharmacy.drug_distribution.entities.DistributorDetail;
import com.pharmacy.drug_distribution.entities.DistributorMedicine;
import com.pharmacy.drug_distribution.entities.MedicineDetails;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    DistributorMedicineDao distributorMedicineDao;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MedicineDetailsDao medicineDetailsDao;

    // @Override
    // public ResponseEntity<?> findByName(String medicineName) {
    //     List<MedicineDetails> medicineDetails = medicineDetailsDao.findByName(medicineName);
    //     if(medicineDetails.isEmpty()){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     }
    //     return ResponseEntity.ok().body(medicineDetails);
    // }

    @Override
    public ResponseEntity<?> addMedicine(AddMedicineDto dto) {
        MedicineDetails medicineDetails = new MedicineDetails();
        modelMapper.map(dto, MedicineDetails.class);
        medicineDetailsDao.save(medicineDetails);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> searchMedicine(String medicineName) {
        List<MedicineDetails> list = medicineDetailsDao.findByNameContaining(medicineName);
        // System.out.println("\n\n" + list + "\n\n");
        List<SearchResultDto> result = new ArrayList<>();
        for(MedicineDetails med:list){
            SearchResultDto dto = modelMapper.map(med, SearchResultDto.class);
            List<Integer> disIdList = new ArrayList<>();
            List<String> disNameList = new ArrayList<>();
            for(DistributorDetail dist:med.getDistributorId()){
                disIdList.add(dist.getId());
                disNameList.add(dist.getFullName());
            }
            dto.setDistributorId(disIdList);
            dto.setDistributorNames(disNameList);
            result.add(dto);
        }
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<?> viewMedicine(Integer medicineId) {
        List<DistributorMedicine> distributorMedicine = distributorMedicineDao.findByMedicineId(medicineDetailsDao.findById(medicineId).orElseThrow());
        List<ViewMedicineDto> medicines = new ArrayList<>();
        for(DistributorMedicine distMed : distributorMedicine){
            ViewMedicineDto temp = new ViewMedicineDto();
            temp.setDistributorId(distMed.getDistributorId().getId());
            temp.setDistributorName(distMed.getDistributorId().getFullName());
            temp.setMedicineId(distMed.getMedicineId().getId());
            temp.setMedicineName(distMed.getMedicineId().getName());
            temp.setPrice(distMed.getPrice());
            medicines.add(temp);
        }
        return ResponseEntity.ok().body(medicines);
    } 
}
