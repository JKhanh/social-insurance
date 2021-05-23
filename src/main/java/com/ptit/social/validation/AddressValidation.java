package com.ptit.social.validation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressValidation {
    public List<String> validate(String province, String district, String ward){
        List<String> messageList = new ArrayList();
        if(province.isEmpty() || district.isEmpty() || ward.isEmpty())
            messageList.add("Missing parameters");
        return messageList;
    }

    public List<String> validate(String address){
        List<String> messageList = new ArrayList();
        if(address.isEmpty())
            messageList.add("Missing address");
        else if(address.equals("-"))
            messageList.add("Not a valid address");
        return messageList;
    }
}
