package com.cpj.hrms.service;

import com.cpj.hrms.model.MCSubmission;
import com.cpj.hrms.repository.MCSubmissionRepository;
import jakarta.transaction.Transactional;
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
public class MCSubmissionService {
    // get access to repository
    @Autowired
    private MCSubmissionRepository mcSubmissionRepository;

    // get all mc submissions
    public List<MCSubmission> findAll() {
        return mcSubmissionRepository.findAll();
    }

    // get mc submission by id
    public MCSubmission findById(Long mcSubmissionId) {
        return mcSubmissionRepository.findById(mcSubmissionId).orElse(null);
    }

    // get mc submissions by employee id
    public List<MCSubmission> findByEmployeeId(Long employeeId) {
        return mcSubmissionRepository.findByEmployee_EmployeeId(employeeId);
    }

    // get mc submissions by date
    public List<MCSubmission> findByMcDate(String mcDate) {
        return mcSubmissionRepository.findByMcDate(mcDate);
    }

    // save mc submission
    public MCSubmission saveMCSubmissionWithFileUpload(MCSubmission mcSubmission, MultipartFile file) throws IOException {
        // save mc submission without file upload
        mcSubmission.setFileUpload(null);
        MCSubmission savedMCSubmission = mcSubmissionRepository.save(mcSubmission);

        // save mc submission with file upload
        if (file != null && !file.isEmpty()) {
            // set file to directory
            String directory = "src/main/resources/static/uploads/mc-submissions/";

            // create directory if it does not exist
            Path path = Path.of(directory);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // set file name
            String fileName = savedMCSubmission.getMcId() + "-" + file.getOriginalFilename();
            Path filePath = path.resolve(fileName);

            // copy file
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // set file name to mc submission
            savedMCSubmission.setFileUpload(fileName);

            // save mc submission
            return mcSubmissionRepository.save(savedMCSubmission);
        }
        // return saved mc submission
        return savedMCSubmission;
    }

    // delete mc submission by id
    public void deleteById(Long mcSubmissionId) {
        mcSubmissionRepository.deleteById(mcSubmissionId);
    }
}
