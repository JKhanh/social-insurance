package com.ptit.social.repository;

import com.ptit.social.model.address.district.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findAllByProvince_Name(String provinceName);
}
