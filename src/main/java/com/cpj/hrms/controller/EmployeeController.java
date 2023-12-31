package com.cpj.hrms.controller;

import com.cpj.hrms.model.Claim;
import com.cpj.hrms.model.Employee;
import com.cpj.hrms.model.LeaveApplication;
import com.cpj.hrms.model.MCSubmission;
import com.cpj.hrms.service.ClaimService;
import com.cpj.hrms.service.EmployeeService;
import com.cpj.hrms.service.LeaveApplicationService;
import com.cpj.hrms.service.MCSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {
    // get access to service layer
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ClaimService claimService;

    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @Autowired
    private MCSubmissionService mcSubmissionService;

    // get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // get employees by position
    @GetMapping("/position/{employeePosition}")
    public List<Employee> getEmployeesByEmployeePosition(@PathVariable String employeePosition) {
        // get employees by position
        List<Employee> employeesByPosition = employeeService.getEmployeesByEmployeePosition(employeePosition);

        // check if employees exist
        if (employeesByPosition != null) {
            return employeesByPosition;
        } else {
            return null;
        }
    }

    // get employee by id
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    // get employee by email address
    @GetMapping("/email/{emailAddress}")
    public Employee getEmployeeByEmailAddress(@PathVariable String emailAddress) {
        return employeeService.getEmployeeByEmailAddress(emailAddress);
    }

    // get employee by ic number
    @GetMapping("/ic/{icNumber}")
    public Employee getEmployeeByIcNumber(@PathVariable String icNumber) {
        return employeeService.getEmployeeByIcNumber(icNumber);
    }

    // post new employee
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws URISyntaxException {
        Employee newEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    // update employee by id
    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @PathVariable Long employeeId) throws URISyntaxException {
        // get employee by id
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);

        // check if employee exists
        if (existingEmployee != null) {
            // update employee
            existingEmployee.setEmployeePassword(employee.getEmployeePassword());
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setQualifications(employee.getQualifications());
            existingEmployee.setSkills(employee.getSkills());
            existingEmployee.setEmployeePosition(employee.getEmployeePosition());
            existingEmployee.setReportsTo(employee.getReportsTo());
            existingEmployee.setEmergencyContact(employee.getEmergencyContact());

            // save employee
            Employee updatedEmployee = employeeService.saveEmployee(existingEmployee);

            // return response
            return ResponseEntity.ok(updatedEmployee);
        } else {
            // return response
            return ResponseEntity.notFound().build();
        }
    }

    // update password by id
    @PutMapping("/password/{employeeId}")
    public ResponseEntity<Employee> updateEmployeePasswordById(@RequestBody Employee employee, @PathVariable Long employeeId) throws URISyntaxException {
        // get employee by id
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);

        // check if employee exists
        if (existingEmployee != null) {
            // update employee
            existingEmployee.setEmployeePassword(employee.getEmployeePassword());

            // save employee
            Employee updatedEmployee = employeeService.saveEmployee(existingEmployee);

            // return response
            return ResponseEntity.ok(updatedEmployee);
        } else {
            // return response
            return ResponseEntity.notFound().build();
        }
    }

    // delete employee by id
    @DeleteMapping("/{employeeId}")
    public String deleteEmployeeById(@PathVariable Long employeeId) {
        // get employee by id
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);

        // check if employee exists
        if (existingEmployee != null) {
            // get list of claims, leave applications and mc submissions by employee id
            List<Claim> claims = claimService.findByEmployeeId(employeeId);
            List<LeaveApplication> leaveApplications = leaveApplicationService.getLeaveApplicationByEmployeeId(employeeId);
            List<MCSubmission> mcSubmissions = mcSubmissionService.findByEmployeeId(employeeId);

            // check and delete related fields in other models
            if (!claims.isEmpty()) {
                for (Claim claim : claims) {
                    claimService.deleteClaimById(claim.getClaimId());
                }
            }

            if (!leaveApplications.isEmpty()) {
                for (LeaveApplication leaveApplication : leaveApplications) {
                    leaveApplicationService.deleteLeaveApplication(leaveApplication.getLeaveId());
                }
            }

            if (!mcSubmissions.isEmpty()) {
                for (MCSubmission mcSubmission : mcSubmissions) {
                    mcSubmissionService.deleteById(mcSubmission.getMcId());
                }
            }

            // delete employee
            employeeService.deleteEmployee(employeeId);

            // return response
            return "Employee deleted successfully";
        } else {
            // return response
            return "Employee not found";
        }
    }

    // login employee
    @PostMapping("/login")
    public ResponseEntity<Employee> loginEmployee(@RequestBody Employee employee) throws URISyntaxException {
        // get employee by email address
        Employee existingEmployee = employeeService.getEmployeeByEmailAddress(employee.getEmailAddress());

        // check if employee exists
        if (existingEmployee != null) {
            // check if password matches
            if (existingEmployee.getEmployeePassword().equals(employee.getEmployeePassword())) {
                // return response
                return ResponseEntity.ok(existingEmployee);
            } else {
                // return response
                return ResponseEntity.notFound().build();
            }
        } else {
            // return response
            return ResponseEntity.notFound().build();
        }
    }
}
