package com.ptit.social.repository;

import com.ptit.social.model.config.annual.AnnualAdjust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualAdjustRepository extends JpaRepository<AnnualAdjust, Integer> {
    AnnualAdjust getFirstByOrderByIdDesc();
}
