package com.ptit.social.validation;

import com.ptit.social.model.config.annual.AnnualAdjustDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnnualAdjustValidation {
    public List<String> validate(AnnualAdjustDTO request){
        List<String> errors = new ArrayList<>();
        if(request.getAdjustment() < 0){
            errors.add("Adjustment cannot be negative");
        }
        if(request.getAdjustment() > 100){
            errors.add("Adjustment cannot be higher than 100%");
        }
        if(request.getYear() < 0) {
            errors.add("Year cannot be negative");
        }

        return errors;
    }
}
