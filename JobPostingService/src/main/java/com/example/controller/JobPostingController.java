package com.example.controller;


import com.example.DTO.JobPostingDTO;
import com.example.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobpostings")
public class JobPostingController {

    @Autowired
    private JobPostingService service;

    @PostMapping
    public ResponseEntity<Void> createJobPosting(@RequestBody JobPostingDTO jobPosting) {
        try {
            service.createJobPosting(jobPosting);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPostingDTO> updateJobPosting(@PathVariable Long id, @RequestBody JobPostingDTO jobPosting) {
        try {
            JobPostingDTO updatedJobPosting = service.updateJobPosting(id, jobPosting);
            return new ResponseEntity<>(updatedJobPosting, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        try {
            service.deleteJobPosting(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDTO> getJobPosting(@PathVariable Long id) {
        try {
            JobPostingDTO jobPosting = service.getJobPosting(id);
            return new ResponseEntity<>(jobPosting, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobPostingDTO>> searchJobPostings(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skill) {
        List<JobPostingDTO> jobPostings = service.searchJobPostings(keyword, location, skill);
        return new ResponseEntity<>(jobPostings, HttpStatus.OK);
    }
}
