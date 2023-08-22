package com.cpj.hrms.controller;

import com.cpj.hrms.model.LeaveApplication;
import com.cpj.hrms.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/leave-applications")
@CrossOrigin
public class LeaveApplicationController {
    // get access to service layer
    @Autowired
    private LeaveApplicationService leaveApplicationService;

    // get all leave applications
    @GetMapping
    public List<LeaveApplication> getAllLeaveApplications() {
        return leaveApplicationService.getAllLeaveApplications();
    }

    @GetMapping("/status")
    public List<LeaveApplication> getAllLeaveApplicationsByStatus(@RequestParam String status) {
        return leaveApplicationService.getLeaveApplicationsByStatus(status);
    }

    // get leave application by id
    @GetMapping("/{leaveId}")
    public LeaveApplication getLeaveApplicationById(@PathVariable Long leaveId) {
        return leaveApplicationService.getLeaveApplicationById(leaveId);
    }

    // get leave application by employee id
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Iterable<LeaveApplication>> getLeaveApplicationByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveApplicationService.getLeaveApplicationByEmployeeId(employeeId));
    }

    // post new leave application
    @PostMapping
    public ResponseEntity<LeaveApplication> saveLeaveApplication(@RequestBody LeaveApplication leaveApplication) throws URISyntaxException {
        LeaveApplication newLeaveApplication = leaveApplicationService.saveLeaveApplication(leaveApplication);
        return ResponseEntity.ok(newLeaveApplication);
    }

    // update leave application by id
    @PutMapping("/{leaveId}")
    public ResponseEntity<LeaveApplication> updateLeaveApplicationById(@RequestBody LeaveApplication leaveApplication, @PathVariable Long leaveId) {
        // get leave application by id
        LeaveApplication existingLeaveApplication = leaveApplicationService.getLeaveApplicationById(leaveId);

        // check if leave application exists
        if (existingLeaveApplication != null) {
            // update leave application
            existingLeaveApplication.setLeaveStatus(leaveApplication.getLeaveStatus());

            // save updated leave application
            LeaveApplication updatedLeaveApplication = leaveApplicationService.saveLeaveApplication(existingLeaveApplication);

            // return updated leave application
            return ResponseEntity.ok(updatedLeaveApplication);
        } else {
            // return null if leave application does not exist
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/approve/{leaveId}")
    public ResponseEntity<LeaveApplication> approveLeaveApplicationById(@PathVariable Long leaveId) {
        // get leave application by id
        LeaveApplication existingLeaveApplication = leaveApplicationService.getLeaveApplicationById(leaveId);

        // check if leave application exists
        if (existingLeaveApplication != null) {
            // update leave application
            existingLeaveApplication.setLeaveStatus("APPROVED");

            // save updated leave application
            LeaveApplication updatedLeaveApplication = leaveApplicationService.saveLeaveApplication(existingLeaveApplication);

            // return updated leave application
            return ResponseEntity.ok(updatedLeaveApplication);
        } else {
            // return null if leave application does not exist
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/reject/{leaveId}")
    public ResponseEntity<LeaveApplication> rejectLeaveApplicationById(@PathVariable Long leaveId) {
        // get leave application by id
        LeaveApplication existingLeaveApplication = leaveApplicationService.getLeaveApplicationById(leaveId);

        // check if leave application exists
        if (existingLeaveApplication != null) {
            // update leave application
            existingLeaveApplication.setLeaveStatus("REJECTED");

            // save updated leave application
            LeaveApplication updatedLeaveApplication = leaveApplicationService.saveLeaveApplication(existingLeaveApplication);

            // return updated leave application
            return ResponseEntity.ok(updatedLeaveApplication);
        } else {
            // return null if leave application does not exist
            return ResponseEntity.notFound().build();
        }
    }

    // delete leave application by id
    @DeleteMapping("/{leaveId}")
    public String deleteLeaveApplicationById(@PathVariable Long leaveId) {
        // get leave application by id
        LeaveApplication existingLeaveApplication = leaveApplicationService.getLeaveApplicationById(leaveId);
        System.out.println(existingLeaveApplication);

        // check if leave application exists
        if (existingLeaveApplication != null) {
            // delete leave application
            leaveApplicationService.deleteLeaveApplication(leaveId);

            // return success message
            return "Leave application deleted successfully";
        } else {
            // return null if leave application does not exist
            return "Leave application not found";
        }
    }
}
