package com.cpj.hrms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "leave_applications")
@Data
public class LeaveApplication {
    // properties
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "leave_id", nullable = false)
    private Long leaveId;

    @Column(name = "leave_date", nullable = false)
    private String leaveDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "leave_status", nullable = false)
    private String leaveStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // getter to get leave date
    public String getLeaveDate() {
        return leaveDate;
    }

    // set the leave date
    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
