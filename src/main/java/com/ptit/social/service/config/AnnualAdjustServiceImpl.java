package com.ptit.social.service.config;

import com.ptit.social.model.config.annual.AnnualAdjust;
import com.ptit.social.model.config.annual.AnnualAdjustDTO;
import com.ptit.social.repository.AnnualAdjustRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnnualAdjustServiceImpl implements AnnualAdjustService{
    private static final Logger logger = LoggerFactory.getLogger(AnnualAdjustServiceImpl.class);

    @Autowired
    private AnnualAdjustRepository repository;

    @Override
    public AnnualAdjustDTO addAnnualAdjust(AnnualAdjustDTO request) {
        if(request != null) {
            AnnualAdjust domain = AnnualAdjust.builder()
                    .adjustment(request.getAdjustment())
                    .year(request.getYear())
                    .build();
            AnnualAdjust annualAdjust = repository.save(domain);
            if(annualAdjust != null){
                return AnnualAdjustDTO.builder()
                        .adjustment(annualAdjust.getAdjustment())
                        .year(annualAdjust.getYear())
                        .build();
            }
            return null;
        }

        return null;
    }

    @Override
    public AnnualAdjustDTO getLatestAnnualAdjust() {
        AnnualAdjust annualAdjust = repository.getFirstByOrderByIdDesc();
        if(annualAdjust != null){
            logger.debug(annualAdjust.toString());
            AnnualAdjustDTO response =
                    AnnualAdjustDTO.builder()
                            .adjustment(annualAdjust.getAdjustment())
                            .year(annualAdjust.getYear())
                            .build();
            return response;
        }
        return null;
    }
}
