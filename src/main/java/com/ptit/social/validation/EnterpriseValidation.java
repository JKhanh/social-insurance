package com.ptit.social.validation;

import com.ptit.social.model.enterprise.EnterpriseRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EnterpriseValidation {
    public List<String> validate(EnterpriseRequest request){
        List<String> messageList = new ArrayList();
        if(request.getName() == null){
            messageList.add("Name cannot be null");
        }
        if(request.getType() != null){
            messageList.add("Type cannot be null");
        }
        return messageList;
    }
}
