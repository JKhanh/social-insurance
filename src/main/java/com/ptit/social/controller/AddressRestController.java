package com.ptit.social.controller;

import com.ptit.social.model.address.district.DistrictDTO;
import com.ptit.social.model.address.provice.ProvinceDTO;
import com.ptit.social.model.address.ward.WardDTO;
import com.ptit.social.service.province.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/address")
@CrossOrigin("*")
public class AddressRestController {
    private static final Logger logger = LoggerFactory.getLogger(AddressRestController.class);

    @Autowired
    private AddressService service;

    @CachePut("addresses")
    @GetMapping(produces = "application/json", value = "/province")
    public ResponseEntity<?> getAllProvince(){
        List<ProvinceDTO> response = service.getAllProvince();
        logger.info("Get all provinces");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CachePut("addresses")
    @GetMapping(produces = "application/json", value = "/district")
    public ResponseEntity<?> getDistrictInProvince(@RequestParam("province") String provinceName){
        List<DistrictDTO> response = service.getAllDistrict(provinceName);
        logger.info("Get all District in " + provinceName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CachePut("addresses")
    @GetMapping(produces = "application/json", value = "/ward")
    public ResponseEntity<?> getWardInDistrict(@RequestParam("district") String districtName){
        List<WardDTO> response = service.getAllWard(districtName);
        logger.info("Get all Ward in " + districtName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
