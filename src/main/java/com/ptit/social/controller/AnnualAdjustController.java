package com.ptit.social.controller;

import com.ptit.social.model.config.annual.AnnualAdjustDTO;
import com.ptit.social.service.config.AnnualAdjustService;
import com.ptit.social.validation.AnnualAdjustValidation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/annual/")
@CrossOrigin("*")
@Slf4j
public class AnnualAdjustController {
    private static final Logger logger = LoggerFactory.getLogger(AnnualAdjustController.class);

    @Autowired
    private AnnualAdjustService service;

    @Autowired
    private AnnualAdjustValidation validation;

    @PostMapping(produces = "application/json")
    private ResponseEntity<?> saveAnnualAdjust(@RequestBody AnnualAdjustDTO request){
        List<String> errors = validation.validate(request);
        if(errors.isEmpty()){
            boolean isSaved = service.addAnnualAdjust(request);
            if(isSaved){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = "application/json")
    private ResponseEntity<?> getLatestAnnualAdjust(){
        AnnualAdjustDTO response = service.getLatestAnnualAdjust();
        if(response != null){
            logger.debug("get Latest annual adjustment");
            logger.debug(response.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.OK);
    }
}
