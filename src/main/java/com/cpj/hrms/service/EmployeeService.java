package com.cpj.hrms.service;

import com.cpj.hrms.model.Employee;
import com.cpj.hrms.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    // get access to repository
    @Autowired
    private EmployeeRepository employeeRepository;

    // find all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // find employees by position
    public List<Employee> getEmployeesByEmployeePosition(String employeePosition) {
        return employeeRepository.findByEmployeePosition(employeePosition);
    }

    // find employee by id
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    // find employee by email address
    public Employee getEmployeeByEmailAddress(String emailAddress) {
        return employeeRepository.findByEmailAddress(emailAddress);
    }

    // find employee by ic number
    public Employee getEmployeeByIcNumber(String icNumber) {
        return employeeRepository.findByIcNumber(icNumber);
    }

    // save employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // delete employee
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
