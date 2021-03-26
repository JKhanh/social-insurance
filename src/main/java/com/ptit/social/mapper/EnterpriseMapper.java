package com.ptit.social.mapper;

import com.ptit.social.model.enterprise.Enterprise;
import com.ptit.social.model.enterprise.EnterpriseRequest;
import com.ptit.social.model.enterprise.EnterpriseResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnterpriseMapper {
    public EnterpriseResponse convertToResponse(Enterprise enterprise){
        EnterpriseResponse response = null;
        if(enterprise != null){
            response = EnterpriseResponse.builder()
                    .id(enterprise.getId())
                    .name(enterprise.getName())
                    .type(enterprise.getType())
                    .build();
        }
        return response;
    }

    public List<EnterpriseResponse> convertToListResponse(List<Enterprise> enterprises){
        if(enterprises != null){
            return enterprises.stream().map(this::convertToResponse).collect(Collectors.toList());
        }
        return null;
    }

    public Enterprise convertToEntity(EnterpriseRequest request){
        Enterprise enterprise = null;
        if(request != null){
            enterprise = Enterprise.builder()
                    .name(request.getName())
                    .type(request.getType())
                    .build();
        }
        return enterprise;
    }
}
