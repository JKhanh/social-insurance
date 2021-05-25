package com.ptit.social.controller;

import com.ptit.social.model.config.minSalary.MinSalary;
import com.ptit.social.service.minSalary.MinSalaryService;
import com.ptit.social.validation.MinSalaryValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/minsalary/")
@CrossOrigin("*")
public class MinSalaryRestController {
    private static final Logger logger = LoggerFactory.getLogger(MinSalaryRestController.class);

    @Autowired
    private MinSalaryService service;

    @Autowired
    private MinSalaryValidation validation;

    @PutMapping(produces = "application/json")
    private ResponseEntity<?> updateMinSalary(@RequestBody MinSalary request){
        if(request != null){
            List<String> errors = validation.validate(request);
            logger.debug("Controller " + request);
            if(errors.isEmpty()){
                MinSalary response = service.updateMinSalary(request);
                logger.debug("Controller response" + response);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = "application/json")
    private ResponseEntity<?> getAllMinSalaries(){
        logger.debug("Get all Salary configure");
        List<MinSalary> response = service.getAllMinSalary();
        for(MinSalary m: response) {
            logger.debug("" + m);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
