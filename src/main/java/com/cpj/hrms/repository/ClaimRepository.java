package com.cpj.hrms.repository;

import com.cpj.hrms.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    // find claim by employee id
    List<Claim> findByEmployee_EmployeeId(Long employeeId);

    // find claim by date
    List<Claim> findByClaimDate(String claimDate);
}
