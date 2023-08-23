package com.cpj.hrms.controller;

import com.cpj.hrms.model.Claim;
import com.cpj.hrms.model.LeaveApplication;
import com.cpj.hrms.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/claims")
@CrossOrigin
public class ClaimController {
    // get access to service layer
    @Autowired
    private ClaimService claimService;

    // get all claims
    @GetMapping
    public ResponseEntity<Iterable<Claim>> findAll() {
        return ResponseEntity.ok(claimService.findAll());
    }

    // get claim by id
    @GetMapping("/{claimId}")
    public ResponseEntity<Claim> findById(@PathVariable Long claimId) {
        return ResponseEntity.ok(claimService.findById(claimId));
    }

    @GetMapping("/status/{claimStatus}")
    public List<Claim> getAllClaimsByClaimStatus(@PathVariable String claimStatus) {
        return claimService.getClaimsByClaimStatus(claimStatus);
    }

    // get claims by employee id
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Iterable<Claim>> findByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(claimService.findByEmployeeId(employeeId));
    }

    // get claims by date
    @GetMapping("/date/{claimDate}")
    public ResponseEntity<Iterable<Claim>> findByClaimDate(@PathVariable String claimDate) {
        return ResponseEntity.ok(claimService.findByClaimDate(claimDate));
    }

    // post new claim
    @PostMapping
    public ResponseEntity<Claim> saveClaimWithFileUpload(@ModelAttribute Claim claim, @RequestParam(name = "file", required = false) MultipartFile file) throws IOException, URISyntaxException {
        Claim newClaim = claimService.saveClaimWithFileUpload(claim, file);
        return ResponseEntity.created(new URI("/claims/" + newClaim.getClaimId())).body(newClaim);
    }

    // update claim
    @PutMapping("/{claimId}")
    public ResponseEntity<Claim> updateClaim(@PathVariable Long claimId, @ModelAttribute Claim claim) throws IOException {
        // get claim by id
        Claim existingClaim = claimService.findById(claimId);

        // check if claim exists
        if (existingClaim != null) {
            // save updated claim
            Claim updatedClaim = claimService.updateClaimStatus(claimId, claim);

            // return response
            return ResponseEntity.ok(updatedClaim);
        }
        // return response
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/approve/{claimId}")
    public ResponseEntity<Claim> approveClaimById(@PathVariable Long claimId) {
        // get leave application by id
        Claim existingClaim = claimService.findById(claimId);

        // check if leave application exists
        if (existingClaim != null) {
            // update leave application
            existingClaim.setClaimStatus("APPROVED");

//            // save updated leave application
            Claim updatedClaim = claimService.updateClaimStatus(claimId, existingClaim);

            // return updated leave applicatioen
            return ResponseEntity.ok(updatedClaim);
        } else {
            // return null if leave application does not exist
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/reject/{claimId}")
    public ResponseEntity<Claim> rejectClaimById(@PathVariable Long claimId) {
        Claim existingClaim = claimService.findById(claimId);

        // check if leave application exists
        if (existingClaim != null) {
            // update leave application
            existingClaim.setClaimStatus("REJECTED");

//            // save updated leave application
            Claim updatedClaim = claimService.updateClaimStatus(claimId, existingClaim);

            // return updated leave applicatioen
            return ResponseEntity.ok(updatedClaim);
        } else {
            // return null if leave application does not exist
            return ResponseEntity.notFound().build();
        }
    }

    // delete claim by id
    @DeleteMapping("/{claimId}")
    public String deleteClaimById(@PathVariable Long claimId) {
        // get claim by id
        Claim existingClaim = claimService.findById(claimId);

        // check if claim exists
        if (existingClaim != null) {
            // delete claim
            claimService.deleteClaimById(claimId);

            // return response
            return "Claim deleted successfully";
        }
        // return response
        return "Claim not found";
    }
}
