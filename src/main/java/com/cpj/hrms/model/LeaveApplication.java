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
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // method to convert the string to local date object
    public LocalDate getLeaveDate() {
        return LocalDate.parse(leaveDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // set the leave date
    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
