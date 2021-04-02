package com.ptit.social.validation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressValidation {
    public List<String> validate(String province, String district, String ward){
        List<String> messageList = new ArrayList();
        if(province == null && district == null && ward == null)
            messageList.add("Missing parameters");
        return messageList;
    }

    public List<String> validate(String address){
        List<String> messageList = new ArrayList();
        if(address == null)
            messageList.add("Missing address");
        else if(address.equals("-"))
            messageList.add("Not a valid address");
        return messageList;
    }
}
