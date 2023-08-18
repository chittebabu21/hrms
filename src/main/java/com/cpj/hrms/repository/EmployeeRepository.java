package com.cpj.hrms.repository;

import com.cpj.hrms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // method to find employee by email address
    Employee findByEmailAddress(String emailAddress);

    // method to find employee by ic number
    Employee findByIcNumber(String icNumber);

    // method to find employees by position
    List<Employee> findByEmployeePosition(String employeePosition);
}
