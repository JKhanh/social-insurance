package com.ptit.social.service.province;

import com.ptit.social.model.address.district.District;
import com.ptit.social.model.address.district.DistrictDTO;
import com.ptit.social.model.address.provice.Province;
import com.ptit.social.model.address.provice.ProvinceDTO;
import com.ptit.social.model.address.ward.Ward;
import com.ptit.social.model.address.ward.WardDTO;
import com.ptit.social.repository.DistrictRepository;
import com.ptit.social.repository.ProvinceRepository;
import com.ptit.social.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<ProvinceDTO> getAllProvince() {
        List<Province> provinceList = provinceRepository.findAll();
        List<ProvinceDTO> response = new ArrayList();
        for(Province province: provinceList){
            response.add(new ProvinceDTO(province.getId(), province.getName()));
        }
        return response;
    }

    @Override
    public List<DistrictDTO> getAllDistrict(String provinceName) {
        List<District> districtList = districtRepository.findAllByProvince_Name(provinceName);
        List<DistrictDTO> response = new ArrayList();
        for(District district: districtList){
            response.add(new DistrictDTO(district.getId(), district.getName()));
        }
        return response;
    }

    @Override
    public List<WardDTO> getAllWard(String districtName) {
        List<Ward> wardList = wardRepository.findAllByDistrict_Name(districtName);
        List<WardDTO> response = new ArrayList();
        for(Ward ward: wardList){
            response.add(new WardDTO(ward.getId(), ward.getName()));
        }
        return response;
    }
}
