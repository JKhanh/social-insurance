package com.ptit.social.repository;

import com.ptit.social.model.insurance.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
}
