package com.ptit.social.service.enterprise;

import com.ptit.social.mapper.EnterpriseMapper;
import com.ptit.social.model.enterprise.Enterprise;
import com.ptit.social.model.enterprise.EnterpriseRequest;
import com.ptit.social.model.enterprise.EnterpriseResponse;
import com.ptit.social.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseServiceImpl implements EnterpriseService{
    @Autowired
    private EnterpriseRepository repository;
    @Autowired
    private EnterpriseMapper mapper;

    @Override
    public EnterpriseResponse addEnterPrise(EnterpriseRequest request) {
        Enterprise enterprise = mapper.convertToEntity(request);
        Enterprise newEnterprise = repository.save(enterprise);

        return mapper.convertToResponse(newEnterprise);
    }

    @Override
    public List<EnterpriseResponse> getAllEnterPrise() {
        List<Enterprise> enterprises = repository.findAll();

        return mapper.convertToListResponse(enterprises);
    }
}
