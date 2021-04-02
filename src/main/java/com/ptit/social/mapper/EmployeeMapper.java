package com.ptit.social.mapper;

import com.ptit.social.model.employee.Employee;
import com.ptit.social.model.employee.EmployeeRequest;
import com.ptit.social.model.employee.EmployeeResponse;
import com.ptit.social.model.enterprise.Enterprise;
import com.ptit.social.model.enterprise.EnterpriseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeMapper.class);

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    public Employee mapToModel(EmployeeRequest request){
        Employee employee = null;
        if(request != null) {
            if (request.getAddress() != null) {
                Enterprise enterprise = enterpriseMapper.convertToEntity(request.getEnterprise());
                employee = Employee.builder()
                        .name(request.getName())
                        .birthday(request.getBirthday())
                        .joinDate(request.getJoinDate())
                        .enterprise(enterprise)
                        .salary(request.getSalary())
                        .position(request.getPosition())
                        .address(request.getAddress())
                        .street(request.getStreet())
                        .build();
            }
        }

        return employee;
    }

    public EmployeeResponse mapToResponse(Employee employee){
        EmployeeResponse response = null;
        if(employee != null){
            EnterpriseResponse enterpriseResponse = enterpriseMapper.convertToResponse(employee.getEnterprise());
            response = EmployeeResponse.builder()
                    .name(employee.getName())
                    .birthday(employee.getBirthday())
                    .joinDate(employee.getJoinDate())
                    .address(employee.getAddress())
                    .salary(employee.getSalary())
                    .position(employee.getPosition())
                    .street(employee.getStreet())
                    .build();
        }
        assert response != null;
        logger.info(response.toString());
        return response;
    }

    public List<EmployeeResponse> mapToResponse(List<Employee> employees){
        List<EmployeeResponse> responseList = new ArrayList();
        for(Employee employee :employees){
            EmployeeResponse employeeResponse = mapToResponse(employee);
            responseList.add(employeeResponse);
        }
        return responseList;
    }
}
