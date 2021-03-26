package com.ptit.social.controller;

import com.ptit.social.model.enterprise.EnterpriseRequest;
import com.ptit.social.model.enterprise.EnterpriseResponse;
import com.ptit.social.service.enterprise.EnterpriseService;
import com.ptit.social.validation.EnterpriseValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enterprise")
@CrossOrigin("*")
public class EnterpriseRestController {
    @Autowired
    private EnterpriseService service;
    @Autowired
    private EnterpriseValidation validation;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addNewEnterPrise(@RequestBody EnterpriseRequest request){
        List<String> error = validation.validate(request);
        if(!error.isEmpty()){
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        EnterpriseResponse response = service.addEnterPrise(request);
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllEnterprise(){
        List<EnterpriseResponse> responses = service.getAllEnterPrise();
        if(!responses.isEmpty()){
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
