package com.ptit.social.repository;

import com.ptit.social.model.address.ward.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
    List<Ward> findAllByDistrict_Name(String districtName);
}
