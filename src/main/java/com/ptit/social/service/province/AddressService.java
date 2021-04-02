package com.ptit.social.service.province;

import com.ptit.social.model.address.district.DistrictDTO;
import com.ptit.social.model.address.provice.ProvinceDTO;
import com.ptit.social.model.address.ward.WardDTO;

import java.util.List;

public interface AddressService {
    List<ProvinceDTO> getAllProvince();
    List<DistrictDTO> getAllDistrict(String provinceName);
    List<WardDTO> getAllWard(String districtName);
}
