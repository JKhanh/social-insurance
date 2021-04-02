package com.ptit.social.service.employee;

import com.ptit.social.mapper.EmployeeMapper;
import com.ptit.social.model.employee.Employee;
import com.ptit.social.model.employee.EmployeeRequest;
import com.ptit.social.model.employee.EmployeeResponse;
import com.ptit.social.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeResponse addNewEmployee(EmployeeRequest request) {
        Employee employee = employeeMapper.mapToModel(request);
        if(employee != null){
            employeeRepository.save(employee);
        }
        return employeeMapper.mapToResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getEmployeeByAddress(String address) {
        List<Employee> employees = new ArrayList();
        if(address != null){
            employees = employeeRepository.findAllByAddressContaining(address);
        }
        return employeeMapper.mapToResponse(employees);
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        logger.info(employees.toString());
        return employeeMapper.mapToResponse(employees);
    }

}