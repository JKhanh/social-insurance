package com.ptit.social.service.employee;

import com.ptit.social.model.employee.EmployeeRequest;
import com.ptit.social.model.employee.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse addNewEmployee(EmployeeRequest request);
    List<EmployeeResponse> getEmployeeByAddress(String address);
    List<EmployeeResponse> getAllEmployee();
}
