package com.ptit.social.service.enterprise;

import com.ptit.social.model.enterprise.EnterpriseRequest;
import com.ptit.social.model.enterprise.EnterpriseResponse;

import java.util.List;

public interface EnterpriseService {
    EnterpriseResponse addEnterPrise(EnterpriseRequest request);
    List<EnterpriseResponse> getAllEnterPrise();
}
