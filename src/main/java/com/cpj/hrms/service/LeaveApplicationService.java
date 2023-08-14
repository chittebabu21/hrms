package com.cpj.hrms.service;

import com.cpj.hrms.model.LeaveApplication;
import com.cpj.hrms.repository.LeaveApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LeaveApplicationService {
    // get access to repository
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    // find all leave applications
    public List<LeaveApplication> getAllLeaveApplications() {
        return leaveApplicationRepository.findAll();
    }

    // find leave application by id
    public LeaveApplication getLeaveApplicationById(Long leaveId) {
        return leaveApplicationRepository.findById(leaveId).orElse(null);
    }

    // find leave application by employee id
    public List<LeaveApplication> getLeaveApplicationByEmployeeId(Long employeeId) {
        return leaveApplicationRepository.findByEmployee_EmployeeId(employeeId);
    }

    // save leave application
    public LeaveApplication saveLeaveApplication(LeaveApplication leaveApplication) {
        return leaveApplicationRepository.save(leaveApplication);
    }

    // delete leave application
    public void deleteLeaveApplication(Long leaveId) {
        leaveApplicationRepository.deleteById(leaveId);
    }
}
