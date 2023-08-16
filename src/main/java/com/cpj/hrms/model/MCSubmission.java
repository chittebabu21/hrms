package com.cpj.hrms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "mc_submissions")
@Data
public class MCSubmission {
    // properties
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "mc_id", nullable = false)
    private Long mcId;

    @Column(name = "mc_date")
    private String mcDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "file_upload")
    private String fileUpload;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // getter for mc date
    public String getMcDate() {
        return mcDate;
    }

    // set the mc date
    public void setMcDate(LocalDate mcDate) {
        this.mcDate = mcDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
