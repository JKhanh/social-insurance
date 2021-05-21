package com.ptit.social;

import com.ptit.social.model.employee.Employee;
import com.ptit.social.repository.EmployeeRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeJPATest {
    @Autowired
    private EmployeeRepository repository;

    private final Employee testEmployee =
            Employee.builder()
                    .address("Xã Nàn Xỉn-Huyện Xín Mần-Tỉnh Hà Giang")
                    .birthday(new Date(1996, Calendar.NOVEMBER, 14))
                    .joinDate(new Date())
                    .name("Name 1")
                    .enterprise(null)
                    .position("Lap trinh vien")
                    .street("So 7 Pho Phung Hung")
                    .salary(10000000L)
            .build();

    @Test
    @Order(1)
    public void insertNewEmployee(){
        Employee newEmployee = repository.save(testEmployee);
        assertThat(newEmployee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void searchEmployeeByAddress(){
        String address = testEmployee.getAddress();
        List<Employee> employees = repository.findAllByAddressContaining(address);
        assertThat(employees.size()).isGreaterThan(0);
        assertThat(employees.get(0).getAddress()).isEqualTo(address);
    }

    @Test
    @Order(3)
    public void getAllEmployees(){
        List<Employee> employees = repository.findAll();
        assertThat(employees).size().isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateEmployee(){
        Employee employee = repository.findByNameIsLike(testEmployee.getName());
        Long newSalary = 50000000L;
        employee.setSalary(newSalary);

        repository.saveAndFlush(employee);

        Employee newEmployee = repository.findByNameIsLike(testEmployee.getName());
        assertThat(newEmployee.getSalary()).isEqualTo(newSalary);
    }

    @Test
    @Order(5)
    public void deleteEmployee(){
        Employee employee = repository.findByNameIsLike(testEmployee.getName());
        repository.delete(employee);

        Employee newEmployee = repository.findByNameIsLike(testEmployee.getName());
        assertThat(newEmployee).isNull();
    }
}
