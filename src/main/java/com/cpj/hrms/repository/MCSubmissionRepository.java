package com.cpj.hrms.repository;

import com.cpj.hrms.model.MCSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MCSubmissionRepository extends JpaRepository<MCSubmission, Long> {
    // find MC submission by employee id
    List<MCSubmission> findByEmployee_EmployeeId(Long employeeId);

    // find MCs by date
    List<MCSubmission> findByMcDate(String mcDate);
}
