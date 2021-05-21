package com.ptit.social.repository;

import com.ptit.social.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByAddressContaining(String address);
    Employee findByNameIsLike(String name);
}
