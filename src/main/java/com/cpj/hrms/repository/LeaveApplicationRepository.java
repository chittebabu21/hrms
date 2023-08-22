package com.cpj.hrms.repository;

import com.cpj.hrms.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
    // find leave application by employee id
    List<LeaveApplication> findByEmployee_EmployeeId(Long employeeId);

    List<LeaveApplication> findByLeaveStatus(String status);
}
