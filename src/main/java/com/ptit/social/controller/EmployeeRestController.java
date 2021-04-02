package com.ptit.social.controller;

import com.ptit.social.model.employee.EmployeeResponse;
import com.ptit.social.service.employee.EmployeeService;
import com.ptit.social.validation.AddressValidation;
import com.ptit.social.validation.EmployeeValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin("*")
public class EmployeeRestController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeValidation employeeValidation;

    @Autowired
    private AddressValidation addressValidation;

    @CachePut("employees")
    @GetMapping(produces = "application/json", value = "/")
    public ResponseEntity<?> getAllEmployee(){
        List<EmployeeResponse> responses = service.getAllEmployee();
        if(!responses.isEmpty()){
            logger.info("Get Employee success");
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } else {
            logger.error("Get Employee failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CachePut("employees")
    @GetMapping(produces = "application/json", value = "/full")
    public ResponseEntity<?> getEmployeeByFullAddress(
            @RequestParam("province") String provinceName,
            @RequestParam("district") String districtName,
            @RequestParam("ward") String wardName
    ){
        List<String> errorList = addressValidation.validate(provinceName, districtName, wardName);
        if(errorList.isEmpty()) {
            String address = wardName + "-" + districtName + "-" + provinceName;
            return findEmployeeByAddress(address);
        } else{
            return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
        }
    }

    @CachePut("employees")
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getEmployeeByAddress(
            @RequestParam("address") String addressName
    ){
        List<String> errorList = addressValidation.validate(addressName);
        if(errorList.isEmpty()) {
            return findEmployeeByAddress(addressName);
        } else{
            return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> findEmployeeByAddress(String address) {
        List<EmployeeResponse> responses = service.getEmployeeByAddress(address);
        if (!responses.isEmpty()) {
            logger.info("Get Employee success");
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } else {
            logger.error("Get Employee failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
