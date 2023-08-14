package com.cpj.hrms.service;

import com.cpj.hrms.model.Claim;
import com.cpj.hrms.repository.ClaimRepository;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Transactional
public class ClaimService {
    // get access to repository
    @Autowired
    private ClaimRepository claimRepository;

    // find all claims
    public List<Claim> findAll() {
        return claimRepository.findAll();
    }

    // find claim by id
    public Claim findById(Long claimId) {
        return claimRepository.findById(claimId).orElse(null);
    }

    // find claim by employee id
    public List<Claim> findByEmployeeId(Long employeeId) {
        return claimRepository.findByEmployee_EmployeeId(employeeId);
    }

    // find claim by date
    public List<Claim> findByClaimDate(String claimDate) {
        return claimRepository.findByClaimDate(claimDate);
    }

    // save claim
    public Claim saveClaimWithFileUpload(Claim claim, MultipartFile file) throws IOException {
        // save claim without file upload
        claim.setFileUpload(null);
        Claim savedClaim = claimRepository.save(claim);

        // save claim with file upload
        if (file != null && !file.isEmpty()) {
            // set file to directory
            String directory = "src/main/resources/static/uploads/claims/";

            // create directory if it does not exist
            Path path = Path.of(directory);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Generate a unique filename for the uploaded file
            String fileName = savedClaim.getClaimId() + "_" + file.getOriginalFilename();
            Path filePath = path.resolve(fileName);

            // Save the uploaded file to the specified directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Update the fileUpload field in the claim object
            savedClaim.setFileUpload(fileName);

            // Update the claim object in the database with the fileUpload field
            savedClaim = claimRepository.save(savedClaim);
        }
        // return saved claim
        return savedClaim;
    }

    // delete claim by id
    public void deleteClaimById(Long claimId) {
        claimRepository.deleteById(claimId);
    }
}
