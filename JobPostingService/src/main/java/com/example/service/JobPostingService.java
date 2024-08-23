package com.example.service;


import com.example.DTO.JobPostingDTO;
import com.example.model.JobPosting;
import com.example.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    public void createJobPosting(JobPostingDTO jobPostingDTO) {
        JobPosting jobPosting = JobPosting.builder()
                .title(jobPostingDTO.getTitle())
                .description(jobPostingDTO.getDescription())
                .company(jobPostingDTO.getCompany())
                .location(jobPostingDTO.getLocation())
                .salaryRange(jobPostingDTO.getSalaryRange())
                .requiredSkills(jobPostingDTO.getRequiredSkills())
                .build();
        jobPostingRepository.save(jobPosting);
    }

    public JobPostingDTO updateJobPosting(Long id, JobPostingDTO jobPostingDTO) {
        Optional<JobPosting> jobPostingOptional = jobPostingRepository.findById(id);
        if (jobPostingOptional.isPresent()) {
            JobPosting jobPosting = JobPosting.builder()
                    .title(jobPostingDTO.getTitle())
                    .description(jobPostingDTO.getDescription())
                    .company(jobPostingDTO.getCompany())
                    .location(jobPostingDTO.getLocation())
                    .salaryRange(jobPostingDTO.getSalaryRange())
                    .requiredSkills(jobPostingDTO.getRequiredSkills())
                    .build();
            jobPostingRepository.save(jobPosting);
        } else {
            throw new RuntimeException("No job with the given id");
        }
        return jobPostingDTO;
    }

    public void deleteJobPosting(Long id) {
        jobPostingRepository.deleteById(id);
    }

    public JobPostingDTO getJobPosting(Long id) {
        Optional<JobPosting> jobPostingOptional = jobPostingRepository.findById(id);
        JobPostingDTO jobPostingDTO = null;
        if (jobPostingOptional.isPresent()) {
            JobPosting jobPosting = jobPostingOptional.get();
            jobPostingDTO = JobPostingDTO.builder()
                    .title(jobPosting.getTitle())
                    .description(jobPosting.getDescription())
                    .company(jobPosting.getCompany())
                    .location(jobPosting.getLocation())
                    .salaryRange(jobPosting.getSalaryRange())
                    .requiredSkills(jobPosting.getRequiredSkills())
                    .build();
        }
        return jobPostingDTO;
    }

    public List<JobPostingDTO> searchJobPostings(String keyword, String location, String skill) {
        List<JobPosting> jobPostings = jobPostingRepository.searchJobPostings(keyword, location, skill);

        return jobPostings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobPostingDTO convertToDto(JobPosting jobPosting) {
        JobPostingDTO dto = new JobPostingDTO();
        dto.setJobId(jobPosting.getId());
        dto.setTitle(jobPosting.getTitle());
        dto.setDescription(jobPosting.getDescription());
        dto.setLocation(jobPosting.getLocation());
        dto.setCompany(jobPosting.getCompany());
        dto.setSalaryRange(jobPosting.getSalaryRange());
        dto.setRequiredSkills(jobPosting.getRequiredSkills());
        return dto;
    }
}
