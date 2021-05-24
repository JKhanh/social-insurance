package com.ptit.social.service.minSalary;

import com.ptit.social.model.config.minSalary.MinSalary;

import java.util.List;

public interface MinSalaryService {
    MinSalary updateMinSalary(MinSalary request);
    List<MinSalary> getAllMinSalary();
}
