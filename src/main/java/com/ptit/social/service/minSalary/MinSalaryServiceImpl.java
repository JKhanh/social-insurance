package com.ptit.social.service.minSalary;

import com.ptit.social.model.config.minSalary.MinSalary;
import com.ptit.social.repository.MinSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinSalaryServiceImpl implements MinSalaryService{
    @Autowired
    private MinSalaryRepository repository;

    @Override
    public MinSalary updateMinSalary(MinSalary request) {
        return repository.saveAndFlush(request);
    }

    @Override
    public List<MinSalary> getAllMinSalary() {
        return repository.findAll();
    }
}
