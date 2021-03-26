package com.ptit.social.repository;

import com.ptit.social.model.enterprise.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {
}
