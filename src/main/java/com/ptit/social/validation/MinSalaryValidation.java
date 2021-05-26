package com.ptit.social.validation;

import com.ptit.social.model.config.minSalary.MinSalary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MinSalaryValidation {
    public List<String> validate(MinSalary request){
        List<String> errors = new ArrayList<>();
        if(request.getId() < 0 || request.getId() > 8){
            errors.add("ID " + request.getId() + " not found");
        }
        if(request.getArea().isEmpty()){
            errors.add("Area is empty");
        }
        if(request.getType() < 1 || request.getType() > 4){
            errors.add("Wrong type");
        }
        if(request.getSalary() < 0){
            errors.add("Wrong salary");
        }
        return errors;
    }
}
