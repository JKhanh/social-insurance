package com.ptit.social.validation;

import com.ptit.social.model.employee.EmployeeRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeValidation {
    public List<String> validate(EmployeeRequest request){
        List<String> messageList = new ArrayList();
        if(request.getName() == null)
            messageList.add("Name cannot be null");
        if(request.getAddress() == null)
            messageList.add("Address cannot be null");
        if(request.getBirthday() == null)
            messageList.add("Birthday cannot be null");
        if(request.getSalary() == null)
            messageList.add("Salary cannot be null");
        if(request.getJoinDate() == null)
            messageList.add("Join date cannot be null");
        if(request.getStreet() == null)
            messageList.add("Street cannot be null");
        return messageList;
    }
}
