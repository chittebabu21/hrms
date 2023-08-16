package com.cpj.hrms.controller;

import com.cpj.hrms.model.MCSubmission;
import com.cpj.hrms.service.MCSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/mc-submissions")
@CrossOrigin
public class MCSubmissionController {
    // get access to service layer
    @Autowired
    private MCSubmissionService mcSubmissionService;

    // get all mc submissions
    @GetMapping
    public ResponseEntity<Iterable<MCSubmission>> findAll() {
        return ResponseEntity.ok(mcSubmissionService.findAll());
    }

    // get mc submission by id
    @GetMapping("/{mcSubmissionId}")
    public ResponseEntity<MCSubmission> findById(@PathVariable Long mcSubmissionId) {
        return ResponseEntity.ok(mcSubmissionService.findById(mcSubmissionId));
    }

    // get mc submissions by employee id
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Iterable<MCSubmission>> findByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(mcSubmissionService.findByEmployeeId(employeeId));
    }

    // get mc submissions by date
    @GetMapping("/date/{mcDate}")
    public ResponseEntity<Iterable<MCSubmission>> findByMcDate(@PathVariable String mcDate) {
        return ResponseEntity.ok(mcSubmissionService.findByMcDate(mcDate));
    }

    // post new mc submission
    @PostMapping
    public ResponseEntity<MCSubmission> saveMCSubmission(@ModelAttribute MCSubmission mcSubmission, @RequestParam(name = "file", required = false) MultipartFile file) throws IOException, URISyntaxException {
        MCSubmission newMCSubmission = mcSubmissionService.saveMCSubmissionWithFileUpload(mcSubmission, file);
        return ResponseEntity.created(new URI("/mc-submissions/" + newMCSubmission.getMcId())).body(newMCSubmission);
    }

    // update mc submission
    @PutMapping("/{mcSubmissionId}")
    public ResponseEntity<MCSubmission> updateMCSubmission(@PathVariable Long mcSubmissionId, @ModelAttribute MCSubmission mcSubmission, @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        // get mc submission by id
        MCSubmission existingMCSubmission = mcSubmissionService.findById(mcSubmissionId);

        // check if mc submission exists
        if (existingMCSubmission != null) {
            // update mc submission
            existingMCSubmission.setReason(mcSubmission.getReason());

            // save mc submission
            MCSubmission savedMCSubmission = mcSubmissionService.saveMCSubmissionWithFileUpload(existingMCSubmission, file);
            return ResponseEntity.ok(savedMCSubmission);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete mc submission
    @DeleteMapping("/{mcSubmissionId}")
    public String deleteMCSubmission(@PathVariable Long mcSubmissionId) {
        // get mc submission by id
        MCSubmission existingMCSubmission = mcSubmissionService.findById(mcSubmissionId);

        // check if mc submission exists
        if (existingMCSubmission != null) {
            // delete mc submission
            mcSubmissionService.deleteById(mcSubmissionId);
            return "MC submission deleted successfully";
        } else {
            return "MC submission not found";
        }
    }
}
