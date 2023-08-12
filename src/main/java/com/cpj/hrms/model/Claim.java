package com.cpj.hrms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "claims")
@Data
public class Claim {
    // properties
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "claim_id", nullable = false)
    private Long claimId;

    @Column(name = "claim_date", nullable = true)
    private String claimDate;

    @Column(name = "claim_type", nullable = false)
    private String claimType;

    @Column(name = "claim_amount", precision = 10, scale = 2)
    private BigDecimal claimAmount;

    @Column(name = "file_upload")
    private String fileUpload;

    @Column(name = "claim_status", nullable = false)
    private String claimStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // method to convert the string to local date object
    public LocalDate getClaimDate() {
        return LocalDate.parse(claimDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // set the claim date
    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
