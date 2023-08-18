package com.cpj.hrms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    // properties
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "employee_password", nullable = false)
    private String employeePassword;

    @Column(name = "ic_number", nullable = false)
    private String icNumber;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "qualifications")
    private String qualifications;

    @Column(name = "skills")
    private String skills;

    @Column(name = "employee_position")
    private String employeePosition;

    @Column(name = "reports_to")
    private String reportsTo;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "created_on", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String createdOn;

    // getter method to return date
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    // set the date of birth
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // method to format timestamp object
    @PrePersist
    private void setCreatedOn() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdOn = dateFormat.format(timestamp);
    }
}
