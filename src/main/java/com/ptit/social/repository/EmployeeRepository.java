package com.ptit.social.repository;

import com.ptit.social.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
