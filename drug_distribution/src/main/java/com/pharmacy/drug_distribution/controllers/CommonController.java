package com.pharmacy.drug_distribution.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.drug_distribution.dtos.AddMedicineDto;
import com.pharmacy.drug_distribution.dtos.LoginDto;
import com.pharmacy.drug_distribution.dtos.RegisterDto;
import com.pharmacy.drug_distribution.services.DistributorService;
import com.pharmacy.drug_distribution.services.MedicineService;
import com.pharmacy.drug_distribution.services.PharmacistService;
import com.pharmacy.drug_distribution.dtos.SearchDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private DistributorService distributorService;

    @Autowired
    private PharmacistService pharmacistService;

    @PostMapping("/DistLogin")
    public ResponseEntity<?> loginDist(@RequestBody LoginDto loginDto) {
        return distributorService.login(loginDto);
    }


    @PostMapping("/DistRegister")
    public ResponseEntity<?> registerDist(@RequestBody RegisterDto registerDto) {
        return distributorService.register(registerDto);
    }

    @PostMapping("/PharmLogin")
    public ResponseEntity<?> pharmDist(@RequestBody LoginDto loginDto) {
        return pharmacistService.login(loginDto);
    }

    @PostMapping("/PharmRegister")
    public ResponseEntity<?> pharmDist(@RequestBody RegisterDto registerDto) {
        return pharmacistService.register(registerDto);
    }
    
    @PostMapping("/searchMedicines")
    public ResponseEntity<?> searchMedicines(@RequestBody SearchDto searchDto) {
        return ResponseEntity.ok().body(medicineService.searchMedicine(searchDto.getSearch()));
    }
    
    @PostMapping("/addMedicine")
    public ResponseEntity<?> addMedicine(@RequestBody AddMedicineDto dto) {
        return medicineService.addMedicine(dto);
    }

    @GetMapping("/viewMedicine/{medicineId}")
    public ResponseEntity<?> viewMedicine(@PathVariable Integer medicineId) {
        return medicineService.viewMedicine(medicineId);
    }
    
    
    
}
