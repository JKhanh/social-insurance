package com.ptit.social.repository;

import com.ptit.social.model.config.minSalary.MinSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinSalaryRepository extends JpaRepository<MinSalary, Integer> {
}
