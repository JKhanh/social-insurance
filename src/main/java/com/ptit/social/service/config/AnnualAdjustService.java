package com.ptit.social.service.config;

import com.ptit.social.model.config.annual.AnnualAdjustDTO;

public interface AnnualAdjustService {
    boolean addAnnualAdjust(AnnualAdjustDTO request);
    AnnualAdjustDTO getLatestAnnualAdjust();
}
